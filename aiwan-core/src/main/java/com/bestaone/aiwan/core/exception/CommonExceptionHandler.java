package com.bestaone.aiwan.core.exception;

import com.bestaone.aiwan.core.api.ApiResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ApiResponse exceptionHandler(Exception e){
        CommonException ce = null;
        if(e instanceof CommonException){
            ce = (CommonException) e;
        } else {
            ce = new CommonException(50000, e.getMessage());
        }
        return ApiResponse.fail(ce);
    }

}
