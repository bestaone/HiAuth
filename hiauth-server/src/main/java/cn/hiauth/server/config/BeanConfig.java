package cn.hiauth.server.config;

import cn.hiauth.server.config.rest.security.ReadonlyFilter;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class BeanConfig {

    @Value("${app.readonly.account:}")
    private String readonlyAccount;

    /**
     * 解决将Long类型转成String
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder
                .serializerByType(Long.class, ToStringSerializer.instance)
                .serializerByType(Long.TYPE, ToStringSerializer.instance);
    }

    @Bean
    @ConditionalOnProperty(prefix = "app.readonly", name = "account")
    public FilterRegistrationBean<ReadonlyFilter> readOnlyFilterRegister() {
        FilterRegistrationBean<ReadonlyFilter> registration = new FilterRegistrationBean<>();
        registration.setOrder(1001);
        registration.setFilter(new ReadonlyFilter(readonlyAccount.split(",")));
        registration.setName("readonlyFilter");
        registration.addUrlPatterns("/api/*");
        log.info("Register ReadonlyFilter,url={}", "/api/*");
        return registration;
    }

}
