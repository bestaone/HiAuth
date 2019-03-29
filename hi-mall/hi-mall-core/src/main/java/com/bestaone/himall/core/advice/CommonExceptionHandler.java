package com.bestaone.himall.core.advice;

import com.bestaone.himall.common.api.ApiResponse;
import com.bestaone.himall.common.exception.CommonException;
import com.bestaone.himall.core.exception.UserNotExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.nio.file.AccessDeniedException;

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

    @ExceptionHandler(UserNotExistException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse userNotExistExceptionHandle(UserNotExistException e) {
        CommonException ce = new CommonException(50001, e.getMessage());
        logger.debug(e.getMessage(), e);
        return ApiResponse.fail(ce);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiResponse accessDeniedExceptionHandler(Exception e){
        CommonException ce = new CommonException(50002, e.getMessage());
        logger.debug(e.getMessage(), e);
        return ApiResponse.fail(ce);
    }

}
