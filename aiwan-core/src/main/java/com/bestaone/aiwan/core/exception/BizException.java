package com.bestaone.aiwan.core.exception;

public class BizException extends CommonException{

    public BizException(Integer code, String msg) {
        super(code, msg);
    }

    public BizException(Integer code, String msg, Throwable cause) {
        super(code, msg, cause);
    }

}
