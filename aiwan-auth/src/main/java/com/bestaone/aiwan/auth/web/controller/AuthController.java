package com.bestaone.aiwan.auth.web.controller;

import com.bestaone.aiwan.auth.api.AuthApi;
import com.bestaone.aiwan.auth.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class AuthController implements AuthApi {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	AccountService accountService;

}
