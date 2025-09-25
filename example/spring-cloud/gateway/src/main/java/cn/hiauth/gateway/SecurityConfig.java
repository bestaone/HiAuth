package cn.hiauth.gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationSuccessHandler;
import org.springframework.security.web.server.authentication.logout.LogoutWebFilter;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;
import org.springframework.security.web.server.authentication.logout.WebSessionServerLogoutHandler;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/login", "logout", "/oauth2/**").permitAll()
                        .anyExchange().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
                        .authenticationSuccessHandler(new RedirectServerAuthenticationSuccessHandler("/home"))
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // 退出登录端点
                        .logoutHandler(new WebSessionServerLogoutHandler()) // 清除会话
                        .logoutSuccessHandler(logoutSuccessHandler()) // 退出成功处理
                )
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .build();
    }

    @Bean
    public ServerLogoutSuccessHandler logoutSuccessHandler() {
        return new ServerLogoutSuccessHandler() {
            @Override
            public Mono<Void> onLogoutSuccess(WebFilterExchange exchange, Authentication authentication) {
                // 重定向到登录页面或首页
                return Mono.fromRunnable(() -> {
                    exchange.getExchange().getResponse().setStatusCode(org.springframework.http.HttpStatus.FOUND);
                    exchange.getExchange().getResponse().getHeaders().setLocation(java.net.URI.create("/login?logout"));
                });
            }
        };
    }

    /**
     * 可选：添加自定义的LogoutWebFilter
     */
    @Bean
    public LogoutWebFilter logoutWebFilter() {
        return new LogoutWebFilter();
    }

}