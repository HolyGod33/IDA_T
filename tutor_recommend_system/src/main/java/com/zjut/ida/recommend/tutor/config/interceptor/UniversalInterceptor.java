package com.zjut.ida.recommend.tutor.config.interceptor;

import com.zjut.ida.recommend.tutor.config.SysStudentHolder;
import com.zjut.ida.recommend.tutor.core.annotation.Privilege;
import com.zjut.ida.recommend.tutor.core.entity.SysStudent;
import com.zjut.ida.recommend.tutor.core.exception.BusinessException;
import com.zjut.ida.recommend.tutor.core.m2nentity.NSysStudent;
import com.zjut.ida.recommend.tutor.dao.SysStudentMapper;
import com.zjut.ida.recommend.tutor.m2ndao.SysStudentDao;
import com.zjut.ida.recommend.tutor.utils.enums.PrivilegeEnum;
import com.zjut.ida.recommend.tutor.utils.enums.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @author wly
 * @date 2021/4/22 10:42
 */
@Slf4j
@Component
public class UniversalInterceptor implements AsyncHandlerInterceptor {
    @Autowired
    private SysStudentHolder stuHolder;
    @Autowired
    private RedisTemplate<String, ?> redisTemplate;
    @Autowired
    private SysStudentMapper studentMapper;

    @Autowired
    private SysStudentDao studentDao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws IOException, ServletException {
        if (!(handler instanceof HandlerMethod)) {
            return false;
        }

        HandlerMethod hm = (HandlerMethod) handler;

        Class<?> clazz = hm.getBeanType();
        Method m = hm.getMethod();

        boolean isClzAnnotation = clazz.isAnnotationPresent(Privilege.class);
        boolean isMethodAnnotation = m.isAnnotationPresent(Privilege.class);
        Privilege privilege = null;

        // ???????????????????????????????????????????????????????????????????????????????????????????????????
        if (isMethodAnnotation) {
            privilege = m.getAnnotation(Privilege.class);
        } else if (isClzAnnotation) {
            privilege = clazz.getAnnotation(Privilege.class);
        }

        // ?????????????????????????????????
        if (privilege == null || privilege.value() == PrivilegeEnum.AllPriv) {
            clear();
            return true;
        }

        // ?????? token
        String token = request.getHeader("token");
        if (token == null) {
            throw new BusinessException(ResponseCode.NOT_AUTHORIZED);
        }

        // ??? redis ??????????????????
        Object tokenObject = redisTemplate.opsForValue().get("token" + token);
        // ????????????
        if (!(tokenObject instanceof NSysStudent)) {
            throw new BusinessException(ResponseCode.LOGIN_EXPIRED);
        }

        // ??????ThreadLocal
        stuHolder.setStudent(studentDao.findNSysStudentByStudentId(((NSysStudent) tokenObject).getStudentId()));
        // token 1????????????
        redisTemplate.expire("token" + token, 1, TimeUnit.HOURS);

        return true;
    }

    private void clear() {
        System.out.println("?????????clear???????????????????????????");
        if (stuHolder.exist()) {
            stuHolder.clear();
        }
    }
}
