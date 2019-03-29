package com.bestaone.himall.config;

import com.bestaone.himall.api.HiAuthApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuth20Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Oauth2Config {

    @Value("${security.oauth2.hiAuth.client.clientId:}")
    private String clientId;
    @Value("${security.oauth2.hiAuth.client.clientSecret:}")
    private String clientSecret;
    @Value("${security.oauth2.hiAuth.client.accessTokenUri:}")
    private String accessTokenUri;
    @Value("${security.oauth2.hiAuth.client.userAuthorizationUri:}")
    private String userAuthorizationUri;

    @Value("${security.oauth2.hiAuth.client.revokeTokenUri:}")
    private String revokeTokenUri;

    @Value("${hiMall.oAuth2.callbackUrl:}")
    private String callbackUrl;

    /**
     * 为防止CSRF跨站攻击，每次请求STATE的值应该不同，可以放入Session！
     * 由于都是localhost测试，所以session无法保持，用一个固定值。
     */
    public static final String STATE = "secret-rensanning";
    private static final String SCOPE = "GOODS+ORDER";


    @Bean
    HiAuthApi hiAuthApi(){
        return new HiAuthApi(accessTokenUri, userAuthorizationUri, revokeTokenUri);
    }

    @Bean
    OAuth20Service oAuth20Service(){
        return new ServiceBuilder(clientId)
                .apiSecret(clientSecret)
                .defaultScope(SCOPE)
                .callback(callbackUrl)
                .build(hiAuthApi());
    }

}
