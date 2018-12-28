package com.bestaone.aiwan.user.exception;

public class DataNotExistException extends ArgumentException {

    private static final Integer code = 10003;

    public DataNotExistException(String message) {
        super(code, message);
    }

    public DataNotExistException(String message, Throwable cause) {
        super(code, message, cause);
    }

}
