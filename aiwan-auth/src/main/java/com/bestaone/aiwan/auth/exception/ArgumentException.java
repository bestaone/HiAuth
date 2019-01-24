package com.bestaone.aiwan.auth.exception;

public class ArgumentException extends CommonException{

    public ArgumentException(Integer code, String message) {
        super(code, message);
    }

    public ArgumentException(Integer code, String message, Throwable cause) {
        super(code, message, cause);
    }

}
