package com.zjut.ida.recommend.tutor.config.interceptor;

import com.zjut.ida.recommend.tutor.config.SysStudentHolder;
import com.zjut.ida.recommend.tutor.core.annotation.Privilege;
import com.zjut.ida.recommend.tutor.core.entity.SysStudent;
import com.zjut.ida.recommend.tutor.core.exception.BusinessException;
import com.zjut.ida.recommend.tutor.dao.SysStudentMapper;
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

        // 如果方法和类声明中同时存在这个注解，那么方法中的会覆盖类中的设定。
        if (isMethodAnnotation) {
            privilege = m.getAnnotation(Privilege.class);
        } else if (isClzAnnotation) {
            privilege = clazz.getAnnotation(Privilege.class);
        }

        // 访问的方法对所有人可见
        if (privilege == null || privilege.value() == PrivilegeEnum.AllPriv) {
            clear();
            return true;
        }

        // 获取 token
        String token = request.getHeader("token");
        if (token == null) {
            throw new BusinessException(ResponseCode.NOT_AUTHORIZED);
        }

        // 从 redis 获取登录信息
        Object tokenObject = redisTemplate.opsForValue().get("token" + token);
        // 登录过期
        if (!(tokenObject instanceof SysStudent)) {
            throw new BusinessException(ResponseCode.LOGIN_EXPIRED);
        }

        // 设置ThreadLocal
        stuHolder.setStudent(studentMapper.selectById(((SysStudent) tokenObject).getStudentId()));
        // token 1小时有效
        redisTemplate.expire("token" + token, 1, TimeUnit.HOURS);

        return true;
    }

    private void clear() {
        if (stuHolder.exist()) {
            stuHolder.clear();
        }
    }
}
