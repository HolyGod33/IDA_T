package com.zjut.ida.recommend.tutor.utils.enums;

/**
 * @author wly
 * @date 2021/4/22 10:42
 */
public enum ResponseCode {
    SUCCESS(0, "成功"),
    FAIL(1, "失败"),
    ERROR(500, "服务器内部错误"),
    NOT_AUTHORIZED(501, "未登录或没有权限"),
    LOGIN_EXPIRED(502, "登录过期"),

    MODEL_ERROR(510, "模型服务内部错误"),

    USER_NOT_EXIST(520, "用户不存在"),
    USER_WRONG_PASSWORD(521, "用户密码错误"),
    USER_EXIST(522, "用户已存在"),
    USER_REGISTER_ERROR(523, "用户注册失败"),
    USER_UPDATE_ERROR(524, "用户信息更新失败"),

    FAVORITE_INSERT_ERROR(530, "收藏失败"),
    FAVORITE_DELETE_ERROR(531, "取消收藏失败"),
    ;

    private final Integer state;
    private final String message;

    ResponseCode(Integer state, String message) {
        this.state = state;
        this.message = message;
    }

    public Integer getState() {
        return state;
    }

    public String getMessage() {
        return message;
    }
}
