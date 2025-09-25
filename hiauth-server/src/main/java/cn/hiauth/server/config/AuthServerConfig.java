package cn.hiauth.server.config;

import cn.hiauth.server.config.web.auth.AuthFailureHandler;
import cn.hiauth.server.config.web.auth.CustomJdbcRegisteredClientRepository;
import cn.hiauth.server.config.web.auth.CustomOidcUserInfoMapper;
import cn.hiauth.server.config.web.auth.FederatedIdentityIdTokenCustomizer;
import cn.hiauth.server.config.web.security.MultiAuthUserService;
import cn.hiauth.server.utils.jose.Jwks;
import cn.webestar.scms.cache.CacheUtil;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.jackson2.SecurityJackson2Modules;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.jackson2.OAuth2AuthorizationServerJackson2Module;
import org.springframework.security.oauth2.server.authorization.oidc.authentication.OidcUserInfoAuthenticationContext;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;

import java.time.Duration;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

/**
 * 认证授权配置
 */
@Configuration
public class AuthServerConfig {

    @Resource
    private CacheUtil cacheUtil;

    @Resource
    private MultiAuthUserService multiAuthUserService;

    @Resource
    private LoginUrlAuthenticationEntryPoint authenticationEntryPoint;

    /**
     * Spring Authorization Server 相关配置，主要配置OAuth 2.1和OpenID Connect 1.0
     */
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        OAuth2AuthorizationServerConfigurer authorizationServerConfigurer = OAuth2AuthorizationServerConfigurer.authorizationServer();
        http
                .securityMatcher(authorizationServerConfigurer.getEndpointsMatcher())
                .with(authorizationServerConfigurer, (authorizationServer) ->
                        authorizationServer
                                .authorizationEndpoint(authorizationEndpoint -> authorizationEndpoint
                                        //自定义授权页
                                        .consentPage("/oauth2/consent")
                                        //自定义授权处理
                                        //.authorizationResponseHandler(customAuthorizationResponseHandler())
                                        //自定义异常处理
                                        .errorResponseHandler(new AuthFailureHandler())
                                )
                                //开启OpenID Connect 1.0（其中oidc为OpenID Connect的缩写，默认配置位于OidcUserInfoAuthenticationProvider
                                .oidc(oidcConfigurer -> oidcConfigurer.userInfoEndpoint(userInfoEndpointConfigurer ->
                                        userInfoEndpointConfigurer.userInfoMapper(oidcUserInfoMapper())
                                ))
                )
                .authorizeHttpRequests((authorize) ->
                        authorize.anyRequest().authenticated()
                )
                // Redirect to the login page when not authenticated from the authorization endpoint
                // 将需要认证的请求，重定向到login进行登录认证。
                .exceptionHandling((exceptions) -> exceptions
                        // 此处自定义，解决登录失败后，在url中保持client_id参数
                        .authenticationEntryPoint(authenticationEntryPoint)
                        .defaultAuthenticationEntryPointFor(
                                authenticationEntryPoint,
                                new MediaTypeRequestMatcher(MediaType.TEXT_HTML)
                        )
                )
                // Accept access tokens for User Info and/or Client Registration
                // 使用jwt处理接收到的access token
                .oauth2ResourceServer((resourceServer) -> resourceServer.jwt(Customizer.withDefaults()));
                //.oauth2ResourceServer((oauth2ResourceServer) -> oauth2ResourceServer
                //   .jwt(Customizer.withDefaults()) // 使用jwt
                //   .authenticationEntryPoint(new MyAuthenticationEntryPoint()) // 请求未携带Token处理
                //   .accessDeniedHandler(new MyAccessDeniedHandler()) // 权限不足处理
                //   //.authenticationFailureHandler(this::failureHandler) // Token解析失败处理
                //);
        return http.build();
    }

    /**
     * jdbc注册客户端管理
     */
    @Bean
    public CustomJdbcRegisteredClientRepository registeredClientRepository(JdbcTemplate jdbcTemplate) {
        CustomJdbcRegisteredClientRepository registeredClientRepository = new CustomJdbcRegisteredClientRepository(jdbcTemplate);
        // this.createDemoRegisteredClient(registeredClientRepository);
        return registeredClientRepository;
    }

    /**
     * 授权信息，对应表：oauth2_authorization
     */
    @Bean
    public OAuth2AuthorizationService authorizationService(JdbcTemplate jdbcTemplate, RegisteredClientRepository registeredClientRepository) {
        JdbcOAuth2AuthorizationService service = new JdbcOAuth2AuthorizationService(jdbcTemplate, registeredClientRepository);
        JdbcOAuth2AuthorizationService.OAuth2AuthorizationRowMapper authorizationRowMapper = new JdbcOAuth2AuthorizationService.OAuth2AuthorizationRowMapper(registeredClientRepository);
        authorizationRowMapper.setLobHandler(new DefaultLobHandler());
        ClassLoader classLoader = JdbcOAuth2AuthorizationService.class.getClassLoader();
        List<Module> securityModules = SecurityJackson2Modules.getModules(classLoader);
        ObjectMapper om = new ObjectMapper();
        om.registerModules(securityModules);
        om.registerModule(new OAuth2AuthorizationServerJackson2Module());
        //om.addMixIn(AuthUser.class, AuthUserMixin.class);
        //om.addMixIn(AuthGrantedAuthority.class, AuthGrantedAuthorityMixin.class);
        om.addMixIn(Long.class, Object.class);
        authorizationRowMapper.setObjectMapper(om);
        service.setAuthorizationRowMapper(authorizationRowMapper);
        return service;
    }

    /**
     * 授权确认，对应表：oauth2_authorization_consent
     */
    @Bean
    public JdbcOAuth2AuthorizationConsentService authorizationConsentService(JdbcTemplate jdbcTemplate, RegisteredClientRepository registeredClientRepository) {
        return new JdbcOAuth2AuthorizationConsentService(jdbcTemplate, registeredClientRepository);
    }


    @Bean
    public Function<OidcUserInfoAuthenticationContext, OidcUserInfo> oidcUserInfoMapper() {
        return new CustomOidcUserInfoMapper(cacheUtil, multiAuthUserService);
    }

    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> idTokenCustomizer() {
        return new FederatedIdentityIdTokenCustomizer();
    }

    /**
     * 配置 JWK，为JWT(id_token)提供加密密钥，用于加密/解密或签名/验签
     * JWK详细见：https://datatracker.ietf.org/doc/html/draft-ietf-jose-json-web-key-41
     */
    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        RSAKey rsaKey = Jwks.generateRsa();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
    }

    /**
     * 配置jwt解析器
     */
    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }

    /**
     * 配置认证服务器请求地址
     */
    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder().build();
    }

    /**
     * 创建一个客户端供测试
     */
    private void createDemoRegisteredClient(JdbcRegisteredClientRepository registeredClientRepository) {

        String clientId = "himall";
        RegisteredClient demoClient = registeredClientRepository.findByClientId(clientId);

        if (demoClient != null) {
            return;
        }

        // JWT（Json Web Token）的配置项：TTL、是否复用refrechToken等等
        TokenSettings tokenSettings = TokenSettings.builder()
                // 令牌存活时间：10小时
                .accessTokenTimeToLive(Duration.ofHours(10))
                .authorizationCodeTimeToLive(Duration.ofMinutes(10))
                // 令牌可以刷新，重新获取
                .reuseRefreshTokens(true)
                // 刷新时间：30天（30天内当令牌过期时，可以用刷新令牌重新申请新令牌，不需要再认证）
                .refreshTokenTimeToLive(Duration.ofDays(30))
                .build();
        // 客户端相关配置
        ClientSettings clientSettings = ClientSettings.builder()
                // 是否需要用户授权确认
                .requireAuthorizationConsent(true)
                .setting("cid", "1")
                .build();

        demoClient = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientName("himall")
                .clientId(clientId)
                .clientSecret(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("123456"))
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .redirectUri("http://127.0.0.1:9000/login/oauth2/code/hiauth-code")
                .redirectUri("http://localhost:9000/login/oauth2/code/hiauth-code")
                .scope(OidcScopes.OPENID)
                .scope(OidcScopes.PROFILE)
                .scope("openid")
                .scope("profile")
                .scope("user")
                // JWT（Json Web Token）配置项
                .tokenSettings(tokenSettings)
                // 客户端配置项
                .clientSettings(clientSettings)
                .build();

        registeredClientRepository.save(demoClient);
    }

}
