package cn.hiauth.auth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${hiauth.cacheControl.maxAge:0}")
    private Integer maxAge;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**", "/webjars/**")
				.addResourceLocations("classpath:/static/", "classpath:/META-INF/resources/webjars/")
				.setCacheControl(CacheControl.maxAge(maxAge, TimeUnit.HOURS).cachePrivate());
	}

}
