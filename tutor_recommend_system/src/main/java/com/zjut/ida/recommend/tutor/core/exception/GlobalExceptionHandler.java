package com.zjut.ida.recommend.tutor.core.exception;

import com.alibaba.fastjson.JSONObject;
import com.zjut.ida.recommend.tutor.utils.enums.ResponseCode;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;
import java.util.Optional;

import static com.zjut.ida.recommend.tutor.utils.Response.response;

/**
 * @author wly
 * @date 2021/4/22 10:42
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Validation-api包里面的异常
     * 实体对象前不加@RequestBody注解
     * 校验方法参数或方法返回值,未校验通过时抛出的异常
     */
    @ExceptionHandler(ValidationException.class)
    public JSONObject validationExceptionHandler(ValidationException e) {
        return response(ResponseCode.FAIL, "方法参数或方法返回值校验失败", e.getCause().getMessage());
    }

    /**
     * spring-context包里面的异常
     * 实体对象前加@RequestBody注解
     * 校验DTO字段,未校验通过时抛出的异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public JSONObject methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        return response(ResponseCode.FAIL, "DTO字段值校验失败", Optional.ofNullable(e.getBindingResult().getFieldError())
                        .orElseGet(() -> new FieldError("", "", "未知字段"))
                        .getDefaultMessage());
    }

    /**
     * 自定义 BusinessException
     */
    @ExceptionHandler(value = BusinessException.class)
    public JSONObject businessExceptionHandler(BusinessException e) {
        e.printStackTrace();
        return response(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public JSONObject otherExceptionsHandler(Exception e) {
        e.printStackTrace();
        return response(ResponseCode.FAIL, e.getMessage());
    }
}
