package com.bestaone.aiwan.auth.exception;

public class IllegalArgumentException extends ArgumentException {

    private static final Integer code = 10001;

    public IllegalArgumentException(String message) {
        super(code, message);
    }

    public IllegalArgumentException(String message, Throwable cause) {
        super(code, message, cause);
    }

}
