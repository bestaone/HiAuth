package com.bestaone.aiwan.auth.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

//	@Autowired
//	private TimeInterceptor timeInterceptor;
//
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(timeInterceptor);
//	}
//
//	@Bean
//	public FilterRegistrationBean timeFilter() {
//		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//		TimeFilter timeFilter = new TimeFilter();
//		registrationBean.setFilter(timeFilter);
//		List<String> urls = new ArrayList<>();
//		urls.add("/*");
//		registrationBean.setUrlPatterns(urls);
//		return registrationBean;
//	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/index").setViewName("index1");
		registry.addViewController("/signin").setViewName("signin");
		registry.addViewController("/signout").setViewName("signout");
	}

}
