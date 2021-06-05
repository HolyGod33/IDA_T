package com.zjut.ida.recommend.tutor.config.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author wly
 * @date 2021/4/22 10:42
 */
@Slf4j
@Aspect
@Component
@Order(1)
public class LogAspect {

    @Pointcut("execution(* com.zjut.ida.recommend.tutor.module.*.controller.*Controller.*(..))")
    public void executeService() {
    }

    @Before("executeService()")
    public void doBefore(JoinPoint joinPoint) {

        // 接收到请求
        Optional<RequestAttributes> attrs = Optional.ofNullable(RequestContextHolder.getRequestAttributes());
        if (!attrs.isPresent()) {
            return;
        }
        HttpServletRequest request = ((ServletRequestAttributes) attrs.get()).getRequest();

        // 记录请求内容
        log.info("请求开始=====================================================================================");
        log.info("URL : " + request.getRequestURL().toString());
        log.info("HTTP_METHOD : " + request.getMethod());
        log.info("IP : " + request.getRemoteAddr());
        log.info("X-Forwarded-For : " + request.getHeader("X-Forwarded-For"));
        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));

        Map<String, Object> paramMap = new HashMap<String, Object>();
        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String paraName = enu.nextElement();
            paramMap.put(paraName, request.getParameter(paraName));
        }
        log.info("REQUEST PARAMETER: " + paramMap);
    }

    @Around(value = "executeService()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 计算方法执行时间
        long startTime = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        log.info("return : " + proceed);
        log.info(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName()
                + " SPEND TIME : " + (System.currentTimeMillis() - startTime) + "ms");
        return proceed;
    }

    @AfterThrowing(value = "executeService()", throwing = "e")
    public void doBefore(JoinPoint joinPoint, Exception e) {
        log.error(e.getMessage());
        log.info("错误请求结束=================================================================================");
    }

    @AfterReturning("executeService()")
    public void doAfterReturning(JoinPoint joinPoint) {
        log.info("请求结束====================================================================================");
    }

}
