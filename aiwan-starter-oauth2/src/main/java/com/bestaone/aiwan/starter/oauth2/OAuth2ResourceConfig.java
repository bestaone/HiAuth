package com.bestaone.aiwan.starter.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@Configuration
@EnableResourceServer
@ConditionalOnProperty(prefix = "auth.oauth2", name = "datasource.url")
public class OAuth2ResourceConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private Environment env;

    @Value("${auth.oauth2.scopes:}")
    private String scopes;

    @Bean
    public TokenStore tokenStore() {
        final DriverManagerDataSource oauth2DataSource = new DriverManagerDataSource();
        oauth2DataSource.setDriverClassName(env.getProperty("auth.oauth2.datasource.driver-class-name"));
        oauth2DataSource.setUrl(env.getProperty("auth.oauth2.datasource.url"));
        oauth2DataSource.setUsername(env.getProperty("auth.oauth2.datasource.username"));
        oauth2DataSource.setPassword(env.getProperty("auth.oauth2.datasource.password"));
        return new JdbcTokenStore(oauth2DataSource);
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        return defaultTokenServices;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer config) {
        config.tokenServices(tokenServices());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        String[] scopeArr = scopes.split("\\|");
        String attribute = "";
        for (String s : scopeArr) {
            attribute += "'" + s + "',";
        }
        attribute = attribute.substring(0,attribute.length()-1);
        String access = "#oauth2.hasAnyScope(" + attribute + ")";

        http.authorizeRequests()
                .antMatchers("/api/**").access(access)
                .antMatchers(
                        "/webjars/**",
                        "/resources/**",
                        "/swagger-ui.html",
                        "/swagger-resources/**",
                        "/v2/api-docs")
                .permitAll();

    }

}