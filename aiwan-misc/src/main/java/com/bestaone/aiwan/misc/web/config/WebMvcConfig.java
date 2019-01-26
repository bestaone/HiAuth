package com.bestaone.aiwan.misc.web.config;

import com.bestaone.aiwan.core.filter.TimeFilter;
import com.bestaone.aiwan.core.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired
	private TimeInterceptor timeInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(timeInterceptor);
	}
	
	@Bean
	public FilterRegistrationBean timeFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		TimeFilter timeFilter = new TimeFilter();
		registrationBean.setFilter(timeFilter);
		List<String> urls = new ArrayList<>();
		urls.add("/*");
		registrationBean.setUrlPatterns(urls);
		return registrationBean;
	}

}
