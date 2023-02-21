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
                .requestMatchers("/api/messages/**").hasAuthority("SCOPE_message.read")
                .requestMatchers("/api/user/**").hasAuthority("SCOPE_user_info")
                .and()
                .oauth2ResourceServer()
                .jwt();
        return http.build();
    }

}
