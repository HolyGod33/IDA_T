package com.zjut.ida.recommend.tutor.core.exception;

import com.zjut.ida.recommend.tutor.utils.enums.ResponseCode;

/**
 * @author wly
 * @date 2021/4/22 10:42
 */
public class BusinessException extends RuntimeException {
    private final ResponseCode code;
    private final String message;

    public BusinessException(ResponseCode code){
        this.code=code;
        this.message=code.getMessage();
    }

    public BusinessException(ResponseCode code, String message){
        this.code=code;
        this.message=message;
    }

    public ResponseCode getCode(){
        return code;
    }

    @Override
    public String getMessage(){
        return message;
    }
}
