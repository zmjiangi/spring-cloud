package com.yijian.api.exception;

/**
 * Created by zmjiangi on 2018-5-28.
 */
public class ProviderException extends RuntimeException {

    private Integer code;
    private String message;

    public ProviderException(String message) {
        super(message);
        this.message = message;
    }

    public ProviderException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
