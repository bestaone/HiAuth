package com.bestaone.himall.core.exception;

public class MissArgumentException extends ArgumentException {

    private static final Integer code = 10002;

    public MissArgumentException(String msg) {
        super(code, msg);
    }

    public MissArgumentException(String msg, Throwable cause) {
        super(code, msg, cause);
    }

}
