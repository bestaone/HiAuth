package com.bestaone.aiwan.core.advice;

import com.bestaone.aiwan.common.api.ApiResponse;
import com.bestaone.aiwan.common.exception.CommonException;
import com.bestaone.aiwan.core.exception.UserNotExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@ExceptionHandler(UserNotExistException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Map<String, Object> handleUserNotExistException(UserNotExistException ex) {
		Map<String, Object> result = new HashMap<>();
		result.put("id", ex.getId());
		result.put("message", ex.getMessage());
		return result;
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ApiResponse exceptionHandler(Exception e){
		CommonException ce = new CommonException(10000, e.getMessage());
		logger.debug(e.getMessage(), e);
		return ApiResponse.fail(ce);
	}

}
