package com.zjut.ida.academic_profile_system.entity;

import com.zjut.ida.academic_profile_system.utils.enums.ResultCode;
import lombok.Data;

/**
 * @author kokoryh on 2022/5/29
 */
@Data
public class Result {

    private Integer result_code;
    private String result_msg = "success";
    private Object data;

    public Result setCode(ResultCode resultCode) {
        this.result_code = resultCode.code;
        return this;
    }

    public Result setMessage(String message) {
        this.result_msg = message;
        return this;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }

}
