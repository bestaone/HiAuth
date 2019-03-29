package com.bestaone.hiauth.config.validatecode;

import com.bestaone.hiauth.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

public class ValidateCodeSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

	@Autowired
	private RedisUtil cacheUtil;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		//账号登录、手机号码登录都需要输入图形验证码
		CaptchaAuthenticationFilter filter = new CaptchaAuthenticationFilter("/signin/form/**", "/signin?error=true");
		filter.setCacheUtil(cacheUtil);
		http.addFilterBefore(filter, AbstractPreAuthenticatedProcessingFilter.class);
	}
	
}
