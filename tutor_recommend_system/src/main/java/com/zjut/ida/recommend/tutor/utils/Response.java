package com.zjut.ida.recommend.tutor.utils;

import com.alibaba.fastjson.JSONObject;
import com.zjut.ida.recommend.tutor.utils.enums.ResponseCode;

/**
 * @author wly
 * @date 2021/4/22 10:42
 */
public class Response {
    public final static String KEY_RESULT_CODE = "result_code";
    public final static String KEY_RESULT_MSG = "result_msg";
    public final static String KEY_DATA = "data";

    public static JSONObject response(ResponseCode code) {
        JSONObject result = new JSONObject();
        result.put(KEY_RESULT_CODE, code.getState());
        result.put(KEY_RESULT_MSG, code.getMessage());
        return result;
    }

    public static JSONObject response(ResponseCode code, String msg) {
        JSONObject result = new JSONObject();
        result.put(KEY_RESULT_CODE, code.getState());
        result.put(KEY_RESULT_MSG, msg);
        return result;
    }

    public static JSONObject response(ResponseCode code, String msg, Object data) {
        JSONObject result = new JSONObject();
        result.put(KEY_RESULT_CODE, code.getState());
        result.put(KEY_RESULT_MSG, msg);
        result.put(KEY_DATA, data);
        return result;
    }

    public static JSONObject bool(boolean code) {
        if (code) {
            return response(ResponseCode.SUCCESS);
        } else {
            return response(ResponseCode.FAIL);
        }
    }

    public static JSONObject ok(Object data) {
        JSONObject result = response(ResponseCode.SUCCESS);
        result.put(KEY_DATA, data);
        return result;
    }

    public static JSONObject fail(Object data) {
        JSONObject result = response(ResponseCode.ERROR);
        result.put(KEY_DATA, data);
        return result;
    }
}
