package com.yijian.api.exception;

/**
 * Created by zmjiangi on 2018-5-29.
 */
public class ErrorResponseEntity {

    private Integer code;
    private String message;

    public ErrorResponseEntity(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

