package cn.hiauth.client;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;
import java.util.Set;

@Data
@ConfigurationProperties("spring.security.oauth2.client.registration.hiauth-code")
public class HiAuthClientRegistrationProperties implements Serializable {

    private String provider;
    private String clientName;
    private String clientId;
    private String clientSecret;
    private String clientAuthenticationMethod;
    private String authorizationGrantType;
    private String redirectUri;
    private Set<String> scope;

}
