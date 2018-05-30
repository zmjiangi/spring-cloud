package com.yijian.api.exception;

/**
 * Created by zmjiangi on 2018-5-29.
 */
public enum ErrorCodeEnum {

    ERROR(1, "一般异常");

    private Integer code;
    private String message;

    ErrorCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
