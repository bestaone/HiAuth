package cn.hiauth.resource.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ResourceServerConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/api/**")
                .authorizeHttpRequests()
                .requestMatchers("/api/**").hasAuthority("SCOPE_message.read")
                .requestMatchers("/api/user/**").hasAuthority("SCOPE_user_info")
                //有个bug，先放行
                .requestMatchers("/api/user/info").permitAll()
                .and()
                .oauth2ResourceServer()
                .jwt();
        return http.build();
    }

}
