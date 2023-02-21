package cn.hiauth.auth.config;

import cn.hiauth.auth.config.multilogin.MultiAuthenticationProvider;
import cn.hiauth.auth.config.multilogin.YzmFilter;
import cn.hiauth.auth.config.multilogin.MultiUserDetailsService;
import cn.hiauth.auth.domain.User;
import cn.hiauth.auth.repository.UserRepository;
import cn.hiauth.auth.utils.CacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Example;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import java.time.LocalDateTime;

@EnableWebSecurity
@Configuration(proxyBeanMethods = true)
public class SecurityConfig {

    @Autowired
    private CacheUtil cacheUtil;

    @Autowired
    private UserRepository userRepository;

    @Bean
    @Order(2)
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http.csrf().disable();

        http.authorizeHttpRequests((authorize) -> {
                authorize.requestMatchers("/login", "/multilogin").permitAll().anyRequest().authenticated();
            })
            .formLogin(fromLogin -> {
                fromLogin.loginPage("/login").loginProcessingUrl("/multilogin").defaultSuccessUrl("/index")
                        .usernameParameter("account");
            })
            .logout(fromLogout -> {
                fromLogout.logoutUrl("/logout").logoutSuccessUrl("/login");
            })
            .csrf(csrfToken -> {
                csrfToken.csrfTokenRepository(new CookieCsrfTokenRepository());
            })
            .oauth2ResourceServer().jwt();

        // 图形验证码过滤器
        YzmFilter filter = new YzmFilter("/multilogin", "/login?error=true");
        filter.setCacheUtil(cacheUtil);
        http.addFilterBefore(filter, AbstractPreAuthenticatedProcessingFilter.class);

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        MultiAuthenticationProvider authenticationProvider = new MultiAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder());
        authenticationProvider.setUserDetailsService(userDetailsService());
        return authenticationProvider;
    }

    @Bean
    public UserDetailsService userDetailsService(){
        createDemoUser();
        return new MultiUserDetailsService(userRepository);
    }

    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers("/static/**", "/webjars/**", "/code/image","/code/sms");
    }

    /**
     * 创建一个客户供测试
     */
    private void createDemoUser(){
        User userParam = new User();
        userParam.setUsername("admin");
        User user = userRepository.findOne(Example.of(userParam)).orElse(null);
        if(user==null){
            user = new User();
            user.setPhoneNum("13712345678");
            user.setCreaterId(0L);
            user.setUpdaterId(0L);
            user.setUsername("admin");
            user.setPassword(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("123456"));
            user.setCreateTime(LocalDateTime.now());
            user.setUpdateTime(LocalDateTime.now());
            user.setRegtime(LocalDateTime.now());
            user.setStatus(1);
            userRepository.save(user);
        }
    }

}
