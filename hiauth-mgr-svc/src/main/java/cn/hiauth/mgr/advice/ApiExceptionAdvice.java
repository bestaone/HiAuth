package cn.hiauth.mgr.advice;

import cn.hiauth.mgr.common.ApiResponse;
import cn.hiauth.mgr.common.BizCode;
import cn.hiauth.mgr.common.BizException;
import cn.hiauth.mgr.common.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import java.nio.file.AccessDeniedException;
import java.util.List;

@Slf4j
@RestControllerAdvice(basePackages = "cn.hiauth.mgr.controller")
public class ApiExceptionAdvice {

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(BindException.class)
    public ApiResponse handle(HttpServletRequest request, BindException e) {
        logger(request, e);
        List<ObjectError> allErrors = e.getAllErrors();
        ObjectError objectError = allErrors.get(0);
        return ApiResponse.fail(objectError.getDefaultMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse handle(HttpServletRequest request, MethodArgumentNotValidException e) {
        logger(request, e);
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        ObjectError objectError = allErrors.get(0);
        return ApiResponse.fail(objectError.getDefaultMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    public ApiResponse handle(HttpServletRequest request, Exception e) {
        logger(request, e);
        CommonException ce = null;
        if (e instanceof CommonException) {
            ce = (CommonException) e;
        } else {
            ce = new CommonException(BizCode.UN_ERROR, e.getMessage());
        }
        return ApiResponse.fail(ce);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(BizException.class)
    public ApiResponse handle(HttpServletRequest request, BizException e) {
        logger(request, e);
        return ApiResponse.fail(e);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(AccessDeniedException.class)
    public ApiResponse handle(HttpServletRequest request, AccessDeniedException e) {
        logger(request, e);
        return ApiResponse.fail(new CommonException(40006, "no permission."));
    }

    private void logger(HttpServletRequest request, Exception e) {
        String contentType = request.getHeader("Content-Type");
        log.error("ERROR URI: {} content-type: {} exception: {}", request.getRequestURI(), contentType, e.toString());
    }

}
