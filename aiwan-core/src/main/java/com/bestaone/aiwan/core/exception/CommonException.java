package com.bestaone.aiwan.core.exception;

public class CommonException extends Exception {

    private Integer code;
    private String msg;

    public CommonException(Integer code, String msg) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public CommonException(Integer code, String msg, Throwable cause) {
        super(msg, cause);
        this.msg = msg;
        this.code = code;
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
