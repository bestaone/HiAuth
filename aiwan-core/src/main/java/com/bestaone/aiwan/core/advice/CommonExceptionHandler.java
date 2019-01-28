package com.bestaone.aiwan.core.advice;

import com.bestaone.aiwan.common.api.ApiResponse;
import com.bestaone.aiwan.common.exception.CommonException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class CommonExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ApiResponse exceptionHandler(Exception e){
        CommonException ce = null;
        if(e instanceof CommonException){
            ce = (CommonException) e;
            logger.debug(e.getMessage());
        } else {
            ce = new CommonException(50000, e.getMessage());
            logger.debug(e.getMessage(), e);
        }
        return ApiResponse.fail(ce);
    }

}
