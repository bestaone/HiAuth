package cn.hiauth.client.session;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author zgs
 */
@Slf4j
@Configuration
@EnableConfigurationProperties({HiAuthClientSessionProperties.class})
public class HiAuthClientSessionAutoConfig {

    @Autowired
    private HiAuthClientSessionProperties hiAuthClientSessionProperties;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnExpression("${app.security.enable:false}")
    public FilterRegistrationBean<AuthFilter> authFilterRegister() {
        FilterRegistrationBean<AuthFilter> registration = new FilterRegistrationBean<>();
        registration.setOrder(FilterRegistrationBean.LOWEST_PRECEDENCE);
        registration.setFilter(new AuthFilter(hiAuthClientSessionProperties, redisTemplate));
        registration.setName("authFilter");
        registration.addUrlPatterns("/*");
        log.info("Register AuthFilter,url=/*");
        return registration;
    }

}
