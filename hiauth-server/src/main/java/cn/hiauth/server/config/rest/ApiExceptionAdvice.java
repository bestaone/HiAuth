package cn.hiauth.server.config.rest;

import cn.webestar.scms.commons.CommonException;
import cn.webestar.scms.commons.R;
import cn.webestar.scms.commons.SysCode;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Set;

/**
 * @author zgs
 */
@Slf4j
@RestControllerAdvice(annotations = ResourceApi.class, basePackages = "cn.webestar.scms.security.controller")
public class ApiExceptionAdvice {

    private static final String ERROR_MSG = "系统异常";

    @Value("${spring.profiles.active:pro}")
    private String profile;

    private Boolean showErrorMsg = false;

    @PostConstruct
    public void init() {
        showErrorMsg = "dev".equals(profile);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(BindException.class)
    public R<?> handle(HttpServletRequest request, BindException e) {
        logger(request, e);
        List<ObjectError> allErrors = e.getAllErrors();
        ObjectError objectError = allErrors.get(0);
        return R.fail(showErrorMsg ? objectError.getDefaultMessage() : ERROR_MSG);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R<CommonException> handle(HttpServletRequest request, MethodArgumentNotValidException e) {
        logger(request, e);
        StringBuilder msg = new StringBuilder("参数不合法：");
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        for (ObjectError oe : allErrors) {
            if (oe instanceof FieldError) {
                msg.append(((FieldError) oe).getField()).append(oe.getDefaultMessage()).append(", ");
            } else {
                msg.append(oe.toString()).append(", ");
            }
        }
        return R.fail(new CommonException(10450, showErrorMsg ? msg.toString() : ERROR_MSG));
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ConstraintViolationException.class)
    public R<CommonException> handle(HttpServletRequest request, ConstraintViolationException e) {
        logger(request, e);
        StringBuilder msg = new StringBuilder("参数不合法：");
        Set<ConstraintViolation<?>> cvs = e.getConstraintViolations();
        for (ConstraintViolation<?> cv : cvs) {
            msg.append(cv.getMessage()).append(", ");
        }
        return R.fail(new CommonException(10450, showErrorMsg ? msg.toString() : ERROR_MSG));
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    public R<CommonException> handle(HttpServletRequest request, Exception e) {
        logger(request, e);
        CommonException ce = null;
        if (e instanceof CommonException) {
            ce = (CommonException) e;
        } else {
            ce = new CommonException(SysCode.ERROR.getCode(), showErrorMsg ? e.getMessage() : ERROR_MSG);
        }
        return R.fail(ce);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(CommonException.class)
    public R<CommonException> handle(HttpServletRequest request, CommonException e) {
        logger(request, e);
        return R.fail(e);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(AccessDeniedException.class)
    public R<CommonException> handle(HttpServletRequest request, AccessDeniedException e) {
        logger(request, e);
        return R.fail(new CommonException(40006, "权限不足."));
    }

    private void logger(HttpServletRequest request, Exception e) {
        log.warn("ERROR URI: {}, exception: {}", request.getRequestURI(), e.getMessage());
        log.debug(e.getMessage(), e);
    }

}
