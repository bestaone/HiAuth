package com.bestaone.himall.core.exception;

import com.bestaone.himall.common.exception.CommonException;

public class ArgumentException extends CommonException {

    public ArgumentException(Integer code, String message) {
        super(code, message);
    }

    public ArgumentException(Integer code, String message, Throwable cause) {
        super(code, message, cause);
    }

}
