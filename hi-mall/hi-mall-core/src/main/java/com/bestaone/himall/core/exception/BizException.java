package com.bestaone.himall.core.exception;

import com.bestaone.himall.common.exception.CommonException;

public class BizException extends CommonException {

    public BizException(Integer code, String msg) {
        super(code, msg);
    }

    public BizException(Integer code, String msg, Throwable cause) {
        super(code, msg, cause);
    }

}
