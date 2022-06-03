package com.zjut.ida.academic_profile_system.utils;

import com.zjut.ida.academic_profile_system.entity.Result;
import com.zjut.ida.academic_profile_system.utils.enums.ResultCode;

/**
 * @author kokoryh on 2022/5/9
 */
public class ResultResponse {
    private static final String DEFAULT_SUCCESS_MESSAGE = "success";

    // 只返回状态
    public static Result getSuccessResult() {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE);
    }

    // 成功返回数据
    public static Result getSuccessResult(Object data) {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }

    // 失败
    public static Result getFailResult(String message) {
        return new Result()
                .setCode(ResultCode.FAIL)
                .setMessage(message);
    }

}
