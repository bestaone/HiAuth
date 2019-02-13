/**
 * 
 */
package com.bestaone.aiwan.auth.web.config.validatecode;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.stereotype.Component;

@Component("validateCodeSecurityConfig")
public class ValidateCodeSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.addFilterBefore(new CaptchaAuthenticationFilter("/signin/form/account", "/signin?error2"), AbstractPreAuthenticatedProcessingFilter.class);
	}
	
}
