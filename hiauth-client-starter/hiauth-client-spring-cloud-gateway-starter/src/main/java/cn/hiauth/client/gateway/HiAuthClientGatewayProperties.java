package cn.hiauth.client.gateway;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Data
@ConfigurationProperties("hiauth.client.gateway")
public class HiAuthClientGatewayProperties {

    private String issuerUri;
    private String authorizationUri;
    private String tokenUri;
    private String userInfoUri;
    private Map<String, Client> clients = new HashMap<>();

    /**
     * 无需登录也无需鉴权的接口(暂时未启用)
     */
    private Set<String> ignoreUris = Set.of("/unpapi/**");

    /**
     * 需要登录，并且需要拥有权限，才可访问的接口
     */
    private Set<String> authUris = Set.of("/api/**");

    /**
     * 需要登录，无需分配权限，就能访问的接口（需要时authUris的子路径）
     */
    private Set<String> ignorePermissionUris = Set.of("/api/common/**");

    @Data
    public static class Client {
        private String clientId;
        private String clientSecret;
        private String[] scope;
        private String redirectUri;
        private String authSuccessRedirectUri;
        private String cachePrefix;
        private Boolean checkPermission;
    }

}