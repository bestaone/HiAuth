package com.bestaone.hiauth.core.advice;

import com.bestaone.hiauth.api.commom.ApiResponse;
import com.bestaone.hiauth.core.exception.CommonException;
import com.bestaone.hiauth.core.exception.UserNotExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class CommonExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 未知的异常，统一处理
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
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

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(UserNotExistException.class)
    public ApiResponse userNotExistExceptionHandle(UserNotExistException e) {
        CommonException ce = new CommonException(50001, e.getMessage());
        logger.debug(e.getMessage(), e);
        return ApiResponse.fail(ce);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AccessDeniedException.class)
    public ApiResponse accessDeniedExceptionHandler(Exception e){
        CommonException ce = new CommonException(50002, e.getMessage());
        logger.debug(e.getMessage(), e);
        return ApiResponse.fail(ce);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException.class)
    public ApiResponse insufficientAuthenticationExceptionHandler(AuthenticationException e){
        //如果捕获到认证异常，将异常继续向上抛给AuthorizationServer
        //AuthorizationServer捕获到异常后，将页面重定向到登录授权页面
        throw e;
    }

}
