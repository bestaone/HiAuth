package cn.hiauth.server.config;

import cn.hiauth.server.config.web.security.*;
import cn.hiauth.server.config.web.security.account.AccountAuthenticationFilter;
import cn.hiauth.server.config.web.security.account.AccountAuthenticationProvider;
import cn.hiauth.server.config.web.security.phone.SmsCodeAuthenticationFilter;
import cn.hiauth.server.config.web.security.phone.SmsCodeAuthenticationProvider;
import cn.hiauth.server.config.web.security.wechat.QrCodeAuthenticationFilter;
import cn.hiauth.server.config.web.security.wechat.QrCodeAuthenticationProvider;
import cn.hiauth.server.mapper.AppMapper;
import cn.webestar.scms.cache.CacheUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.HashMap;
import java.util.Map;

/**
 * 安全管理配置
 */
@Configuration(proxyBeanMethods = true)
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true, securedEnabled = true)
public class SecurityConfig {

    @Value("${smsUils.superSmsCode:}")
    private String superSmsCode;

    @Resource
    private CacheUtil cacheUtil;

    @Resource
    private MultiAuthUserService multiAuthUserService;

    @Resource
    private AppMapper appMapper;

    @Bean
    public PasswordEncoder passwordEncoder() {
        String encodingId = "bcrypt";
        Map<String, PasswordEncoder> encoders = new HashMap<>(8);
        encoders.put(encodingId, new BCryptPasswordEncoder());
        return new DelegatingPasswordEncoder(encodingId, encoders);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login").permitAll()
                        .anyRequest().authenticated()
                )
                .cors(Customizer.withDefaults())
                // 跨站攻击防护，默认开启，两个登录的应用先后退出，第二个退出操作会导致前一个退出后的登录页面中的csrf token失效
                // 禁用csrf能够解决这个问题，但是会带来安全风险，所以这里不禁用
                //.csrf(AbstractHttpConfigurer::disable)
                //.formLogin(Customizer.withDefaults())
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
                .exceptionHandling(exceptions -> exceptions
                        // 此处添加AuthenticationEntryPoint，会在登录失败后被执行
                        // 此处自定义，解决登录失败后，在url中保持client_id参数
                        .authenticationEntryPoint(authenticationEntryPoint())
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
     * 定义securityContextRepository，加入两种securityContextRepository
     */
    @Bean
    public SecurityContextRepository securityContextRepository() {
        HttpSessionSecurityContextRepository httpSecurityRepository = new HttpSessionSecurityContextRepository();
        return new DelegatingSecurityContextRepository(httpSecurityRepository, new RequestAttributeSecurityContextRepository());
    }

    /**
     * 自定义登录成功处理器
     */
    @Bean
    public SavedRequestAwareAuthenticationSuccessHandler authenticationSuccessHandler() {
        SavedRequestAwareAuthenticationSuccessHandler authenticationSuccessHandler = new CustomAuthenticationSuccessHandler();
        authenticationSuccessHandler.setRequestCache(httpSessionRequestCache());
        return authenticationSuccessHandler;
    }

    /**
     * 自定义登录失败处理器
     */
    @Bean
    public SimpleUrlAuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler("/login?error");
    }

    /**
     * 自定义自定义登录页面
     */
    @Bean
    public LoginUrlAuthenticationEntryPoint authenticationEntryPoint() {
        return new CustomLoginUrlAuthenticationEntryPoint("/login");
    }

    /**
     * 请求缓存
     */
    @Bean
    public HttpSessionRequestCache httpSessionRequestCache() {
        HttpSessionRequestCache requestCache = new MultiAppHttpSessionRequestCache();
        requestCache.setMatchingRequestParameterName("continue");
        return requestCache;
    }

    /**
     * 账号登录过滤器
     */
    @Bean
    public AccountAuthenticationFilter accountAuthenticationFilter() {
        AccountAuthenticationFilter accountAuthenticationFilter = new AccountAuthenticationFilter("/account/doLogin");
        accountAuthenticationFilter.setAuthenticationManager(authenticationManager());
        accountAuthenticationFilter.setSecurityContextRepository(securityContextRepository());
        accountAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
        accountAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler());
        return accountAuthenticationFilter;
    }

    /**
     * 手机验证码登录过滤器
     */
    @Bean
    public SmsCodeAuthenticationFilter smsCodeAuthenticationFilter() {
        SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter("/phone/doLogin");
        smsCodeAuthenticationFilter.setAuthenticationManager(authenticationManager());
        smsCodeAuthenticationFilter.setSecurityContextRepository(securityContextRepository());
        smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
        smsCodeAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler());
        return smsCodeAuthenticationFilter;
    }

    /**
     * 微信二维码登录过滤器
     */
    @Bean
    public QrCodeAuthenticationFilter qrCodeAuthenticationFilter() {
        QrCodeAuthenticationFilter qrCodeAuthenticationFilter = new QrCodeAuthenticationFilter("/wechat/qrcode/doLogin");
        qrCodeAuthenticationFilter.setAuthenticationManager(authenticationManager());
        qrCodeAuthenticationFilter.setSecurityContextRepository(securityContextRepository());
        qrCodeAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
        qrCodeAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler());
        return qrCodeAuthenticationFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        // 账号登录provider
        AccountAuthenticationProvider accountAuthenticationProvider = new AccountAuthenticationProvider();
        accountAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        accountAuthenticationProvider.setHttpSessionRequestCache(httpSessionRequestCache());
        accountAuthenticationProvider.setCacheUtil(cacheUtil);
        accountAuthenticationProvider.setUserDetailsService(multiAuthUserService);
        accountAuthenticationProvider.setAppMapper(appMapper);
        // 手机验证码登录provider
        SmsCodeAuthenticationProvider smsCodeAuthenticationProvider = new SmsCodeAuthenticationProvider();
        smsCodeAuthenticationProvider.setHttpSessionRequestCache(httpSessionRequestCache());
        smsCodeAuthenticationProvider.setCacheUtil(cacheUtil);
        smsCodeAuthenticationProvider.setUserDetailsService(multiAuthUserService);
        smsCodeAuthenticationProvider.setSuperSmsCode(superSmsCode);
        smsCodeAuthenticationProvider.setAppMapper(appMapper);
        // 微信二维码登录provider
        QrCodeAuthenticationProvider qrCodeAuthenticationProvider = new QrCodeAuthenticationProvider();
        qrCodeAuthenticationProvider.setHttpSessionRequestCache(httpSessionRequestCache());
        qrCodeAuthenticationProvider.setUserDetailsService(multiAuthUserService);
        qrCodeAuthenticationProvider.setAppMapper(appMapper);
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
                AntPathRequestMatcher.antMatcher("/auth/code/captcha"),
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
