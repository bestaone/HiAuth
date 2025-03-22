package cn.hiauth.server.config;

import cn.hiauth.server.config.rest.ResourceAccessDeniedHandler;
import cn.hiauth.server.config.rest.ResourceAuthenticationEntryPoint;
import cn.hiauth.server.config.web.security.CaptchaFilter;
import cn.hiauth.server.config.web.security.MultiAuthUserService;
import cn.hiauth.server.config.web.security.MultiAuthenticationProvider;
import cn.webestar.scms.cache.CacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.HashMap;
import java.util.Map;

@Configuration(proxyBeanMethods = true)
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true, securedEnabled = true)
public class SecurityConfig {

    @Autowired
    private CacheUtil cacheUtil;

    @Autowired
    private MultiAuthUserService multiAuthUserService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        String encodingId = "bcrypt";
        Map<String, PasswordEncoder> encoders = new HashMap<>(8);
        encoders.put(encodingId, new BCryptPasswordEncoder());
        return new DelegatingPasswordEncoder(encodingId, encoders);
    }

    /**
     * Spring Security 过滤链配置（此处是纯Spring Security相关配置）
     */
    @Bean
    @Order(2)
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(new AntPathRequestMatcher("/login")).permitAll()
                        .anyRequest().authenticated()
                )
                // Form login handles the redirect to the login page from the
                // authorization server filter chain
                .formLogin(Customizer.withDefaults())
                .formLogin(form -> form.loginPage("/login")
                        .loginProcessingUrl("/auth/doLogin")
                        .usernameParameter("account")
                )
                //.rememberMe(AbstractHttpConfigurer::disable)
                //.exceptionHandling(e -> e.accessDeniedPage("/oauth2/error"))
                //.cors(Customizer.withDefaults())
                //.csrf(AbstractHttpConfigurer::disable)
                // 设置资源服务配置，请求头中懈怠了 Bearer Token的请求会被拦截处理
                .oauth2ResourceServer((oauth2ResourceServer) -> oauth2ResourceServer
                                .jwt(Customizer.withDefaults()) // 使用jwt
                                .authenticationEntryPoint(new ResourceAuthenticationEntryPoint()) // 请求未携带Token处理
                                .accessDeniedHandler(new ResourceAccessDeniedHandler())     // 权限不足处理
                        //.authenticationFailureHandler(this::failureHandler)       // Token解析失败处理
                );

        // 图形验证码过滤器
        CaptchaFilter filter = new CaptchaFilter("/auth/doLogin", "/login?error");
        filter.setCacheUtil(cacheUtil);
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public MultiAuthenticationProvider authProvider() {
        MultiAuthenticationProvider authenticationProvider = new MultiAuthenticationProvider();
        authenticationProvider.setMultiAuthUserService(multiAuthUserService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setCacheUtil(cacheUtil);
        return authenticationProvider;
    }

    /**
     * 不走过滤器链的放行配置
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(
                AntPathRequestMatcher.antMatcher("/api/**"),
                AntPathRequestMatcher.antMatcher("/unpapi/**"),
                AntPathRequestMatcher.antMatcher("/auth/code/image"),
                AntPathRequestMatcher.antMatcher("/auth/code/sms"),
                AntPathRequestMatcher.antMatcher("/actuator/**"),
                AntPathRequestMatcher.antMatcher("/static/**"),
                AntPathRequestMatcher.antMatcher("/favicon.ico"),
                AntPathRequestMatcher.antMatcher("/webjars/**"),
                AntPathRequestMatcher.antMatcher("/doc.html"),
                AntPathRequestMatcher.antMatcher("/swagger-resources/**"),
                AntPathRequestMatcher.antMatcher("/v3/api-docs/**"),
                AntPathRequestMatcher.antMatcher("/swagger-ui/**")
        );
    }

}
