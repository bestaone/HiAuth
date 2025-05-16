package cn.hiauth.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.client.RestTemplate;

/**
 * @author zgs
 */
@Slf4j
@Configuration
@EnableConfigurationProperties({HiAuthClientProviderProperties.class, HiAuthClientRegistrationProperties.class, HiAuthClientProperties.class})
public class HiAuthClientAutoConfig {

    @Autowired
    private HiAuthClientProviderProperties hiAuthClientProviderProperties;

    @Autowired
    private HiAuthClientRegistrationProperties hiAuthClientRegistrationProperties;

    @Autowired
    private HiAuthClientProperties authClientProperties;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Bean
    @ConditionalOnMissingBean(RestTemplate.class)
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnExpression("${app.security.enable:false}")
    public FilterRegistrationBean<AuthFilter> authFilterRegister() {
        FilterRegistrationBean<AuthFilter> registration = new FilterRegistrationBean<>();
        registration.setOrder(FilterRegistrationBean.LOWEST_PRECEDENCE);
        registration.setFilter(new AuthFilter(authClientProperties, redisTemplate));
        registration.setName("authFilter");
        registration.addUrlPatterns("/*");
        log.info("Register AuthFilter,url=/*");
        return registration;
    }


}
