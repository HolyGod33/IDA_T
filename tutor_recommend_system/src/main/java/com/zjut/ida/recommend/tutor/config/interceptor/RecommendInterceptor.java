package com.zjut.ida.recommend.tutor.config.interceptor;

import com.zjut.ida.recommend.tutor.config.SysStudentHolder;
import com.zjut.ida.recommend.tutor.core.entity.SysStudent;
import com.zjut.ida.recommend.tutor.dao.SysStudentMapper;
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
import java.util.concurrent.TimeUnit;

/**
 * @author wly
 * @date 2021/4/22 10:42
 */
@Slf4j
@Component
public class RecommendInterceptor implements AsyncHandlerInterceptor {
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

        // 获取 token
        String token = request.getHeader("token");
        if (token == null) {
            return true;
        }

        // 从 redis 获取登录信息
        Object tokenObject = redisTemplate.opsForValue().get("token" + token);
        // 登录过期
        if (!(tokenObject instanceof SysStudent)) {
            return true;
        }

        // 设置ThreadLocal
        stuHolder.setUser(studentMapper.selectById(((SysStudent) tokenObject).getStudentId()));
        // token 1小时有效
        redisTemplate.expire("token" + token, 1, TimeUnit.HOURS);

        return true;
    }
}
