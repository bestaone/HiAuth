package cn.hiauth.mgr.config;

import cn.hiauth.mgr.config.security.DefaultLogoutHandler;
import cn.hiauth.mgr.config.security.JwtAuthenticationFilter;
import cn.hiauth.mgr.config.security.JwtLoginFilter;
import cn.hiauth.mgr.utils.CacheUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class DefaultSecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CacheUtil cacheUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http.authenticationManager(authenticationManager());
        http.userDetailsService(userDetailsService);
        http
                //关闭跨站请求防护
                .cors().and().csrf().disable()
                .authorizeRequests()
                //允许不登陆就可以访问的方法，多个用逗号分隔
//                .antMatchers("/test").permitAll()
                //其他的需要授权后访问
                .anyRequest().authenticated()
                .and().logout().logoutUrl("/api/logout")
                .addLogoutHandler(new DefaultLogoutHandler())
                .and()
                //增加登录拦截,登录处理,生成token
                .addFilterBefore(new JwtLoginFilter(authenticationManager(), objectMapper, cacheUtil), UsernamePasswordAuthenticationFilter.class)
                //增加是否登陸过滤,校验token
                .addFilterBefore(new JwtAuthenticationFilter(authenticationManager(), objectMapper, cacheUtil), BasicAuthenticationFilter.class)
                // 前后端分离是无状态的，所以暫時不用session，將登陆信息保存在token中。
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }

    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers("/doc.html", "/favicon.ico", "/swagger-resources/**", "/webjars/**", "/swagger/**", "/v3/api-docs/**", "/regist");
    }

}
