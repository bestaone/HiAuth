package cn.hiauth.server.config;

import cn.hiauth.server.config.web.security.MultiAuthUserService;
import cn.hiauth.server.config.web.security.account.AccountAuthenticationFilter;
import cn.hiauth.server.config.web.security.account.AccountAuthenticationProvider;
import cn.hiauth.server.config.web.security.phone.SmsCodeAuthenticationFilter;
import cn.hiauth.server.config.web.security.phone.SmsCodeAuthenticationProvider;
import cn.hiauth.server.config.web.security.wechat.QrCodeAuthenticationFilter;
import cn.hiauth.server.config.web.security.wechat.QrCodeAuthenticationProvider;
import cn.webestar.scms.cache.CacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
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
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.HashMap;
import java.util.Map;

@Configuration(proxyBeanMethods = true)
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true, securedEnabled = true)
public class SecurityConfig {

    @Value("${smsUils.superSmsCode:}")
    private String superSmsCode;

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
     * 定义securityContextRepository，加入两种securityContextRepository
     */
    @Bean
    public SecurityContextRepository securityContextRepository() {
        HttpSessionSecurityContextRepository httpSecurityRepository = new HttpSessionSecurityContextRepository();
        return new DelegatingSecurityContextRepository(httpSecurityRepository, new RequestAttributeSecurityContextRepository());
    }

    @Bean
    @Order(2)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(Customizer.withDefaults())
                // 用户名密码登录配置
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/account/doLogin")
                        .permitAll()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/phone/doLogin")
                        .permitAll()
                )
                // 用户名证码认证过滤器
                .addFilterBefore(accountAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                // 添加手机验证码认证过滤器
                .addFilterBefore(smsCodeAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                // 微信二维码登录认证过滤器
                .addFilterBefore(qrCodeAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                // 设置全局authenticationManager
                .authenticationManager(authenticationManager())
                // 设置全局securityContextRepository
                .securityContext(c -> c.securityContextRepository(securityContextRepository()));
        return http.build();
    }

    /**
     * 账号登录过滤器
     */
    @Bean
    public AccountAuthenticationFilter accountAuthenticationFilter() {
        AccountAuthenticationFilter smsCodeAuthenticationFilter = new AccountAuthenticationFilter("/account/doLogin", "/login?error");
        smsCodeAuthenticationFilter.setAuthenticationManager(authenticationManager());
        smsCodeAuthenticationFilter.setSecurityContextRepository(securityContextRepository());
        return smsCodeAuthenticationFilter;
    }

    /**
     * 手机验证码登录过滤器
     */
    @Bean
    public SmsCodeAuthenticationFilter smsCodeAuthenticationFilter() {
        SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter("/phone/doLogin", "/login?error");
        smsCodeAuthenticationFilter.setAuthenticationManager(authenticationManager());
        smsCodeAuthenticationFilter.setSecurityContextRepository(securityContextRepository());
        return smsCodeAuthenticationFilter;
    }

    /**
     * 微信二维码登录过滤器
     */
    @Bean
    public QrCodeAuthenticationFilter qrCodeAuthenticationFilter() {
        QrCodeAuthenticationFilter qrCodeAuthenticationFilter = new QrCodeAuthenticationFilter("/wechat/qrcode/doLogin", "/login?error");
        qrCodeAuthenticationFilter.setAuthenticationManager(authenticationManager());
        qrCodeAuthenticationFilter.setSecurityContextRepository(securityContextRepository());
        return qrCodeAuthenticationFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        // 账号登录provider
        AccountAuthenticationProvider accountAuthenticationProvider = new AccountAuthenticationProvider();
        accountAuthenticationProvider.setCacheUtil(cacheUtil);
        accountAuthenticationProvider.setUserDetailsService(multiAuthUserService);
        accountAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        // 手机验证码登录provider
        SmsCodeAuthenticationProvider smsCodeAuthenticationProvider = new SmsCodeAuthenticationProvider();
        smsCodeAuthenticationProvider.setCacheUtil(cacheUtil);
        smsCodeAuthenticationProvider.setUserDetailsService(multiAuthUserService);
        smsCodeAuthenticationProvider.setSuperSmsCode(superSmsCode);
        // 微信二维码登录provider
        QrCodeAuthenticationProvider qrCodeAuthenticationProvider = new QrCodeAuthenticationProvider();
        qrCodeAuthenticationProvider.setUserDetailsService(multiAuthUserService);
        return new ProviderManager(accountAuthenticationProvider, smsCodeAuthenticationProvider, qrCodeAuthenticationProvider);
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
                AntPathRequestMatcher.antMatcher("/swagger-ui/**"),
                AntPathRequestMatcher.antMatcher("/docs/**")
        );
    }

}
