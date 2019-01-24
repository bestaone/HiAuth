package com.bestaone.aiwan.auth.web.controller;

import com.bestaone.aiwan.auth.api.UserApi;
import com.bestaone.aiwan.auth.service.AccountService;
import com.bestaone.aiwan.core.api.ApiResponse;
import com.bestaone.aiwan.core.exception.CommonException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController implements UserApi {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	AccountService accountService;

	@Override
	public ApiResponse login(String username, String password) throws CommonException {
		return null;
	}

	@Override
	public ApiResponse registe(String username, String password) throws CommonException {
		return null;
	}

}
