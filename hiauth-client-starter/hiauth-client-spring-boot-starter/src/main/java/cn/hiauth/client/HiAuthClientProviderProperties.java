package cn.hiauth.client;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

@Data
@ConfigurationProperties("spring.security.oauth2.client.provider.hiauth-server")
public class HiAuthClientProviderProperties implements Serializable {

    private String issuerUri;
    private String authorizationUri;
    private String tokenUri;
    private String userInfoUri;
    private String jwkSetUri;

}
