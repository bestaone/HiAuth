package cn.hiauth.wechatlogin.config;

import cn.hiauth.wechatlogin.config.web.security.phone.SmsCodeAuthenticationFilter;
import cn.hiauth.wechatlogin.config.web.security.phone.SmsCodeAuthenticationProvider;
import cn.hiauth.wechatlogin.config.web.security.wechat.QrCodeAuthenticationFilter;
import cn.hiauth.wechatlogin.config.web.security.wechat.QrCodeAuthenticationProvider;
import cn.hiauth.wechatlogin.service.CustomUserDetailsService;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.client.RestTemplate;

@EnableWebSecurity
@Configuration(proxyBeanMethods = true)
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CustomUserDetailsService customUserDetailsService() {
        return new CustomUserDetailsService(passwordEncoder());
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers("/static/**", "/webjars/**");
    }

    /**
     * 定义securityContextRepository，加入两种securityContextRepository
     */
    @Bean
    public SecurityContextRepository securityContextRepository() {
        HttpSessionSecurityContextRepository httpSecurityRepository = new HttpSessionSecurityContextRepository();
        DelegatingSecurityContextRepository defaultRepository = new DelegatingSecurityContextRepository(httpSecurityRepository, new RequestAttributeSecurityContextRepository());
        return defaultRepository;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/home").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(Customizer.withDefaults())
                // 用户名密码登录配置
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/account/doLogin")
                        .defaultSuccessUrl("/index", true)
                        .permitAll()
                )
                // 添加手机验证码认证过滤器
                .addFilterBefore(smsCodeAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(qrCodeAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                // 设置全局authenticationManager
                .authenticationManager(authenticationManager())
                // 设置全局securityContextRepository
                .securityContext(c -> c.securityContextRepository(securityContextRepository()))
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

    @Bean
    public SmsCodeAuthenticationFilter smsCodeAuthenticationFilter() {
        // 手机验证码登录过滤器
        SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter();
        smsCodeAuthenticationFilter.setAuthenticationManager(authenticationManager());
        smsCodeAuthenticationFilter.setSecurityContextRepository(securityContextRepository());
        return smsCodeAuthenticationFilter;
    }

    @Bean
    public QrCodeAuthenticationFilter qrCodeAuthenticationFilter() {
        QrCodeAuthenticationFilter qrCodeAuthenticationFilter = new QrCodeAuthenticationFilter();
        qrCodeAuthenticationFilter.setAuthenticationManager(authenticationManager());
        qrCodeAuthenticationFilter.setSecurityContextRepository(securityContextRepository());
        return qrCodeAuthenticationFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(customUserDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        SmsCodeAuthenticationProvider smsCodeAuthenticationProvider = new SmsCodeAuthenticationProvider(customUserDetailsService());
        QrCodeAuthenticationProvider qrCodeAuthenticationProvider = new QrCodeAuthenticationProvider(customUserDetailsService());
        return new ProviderManager(daoAuthenticationProvider, smsCodeAuthenticationProvider, qrCodeAuthenticationProvider);
    }

}


