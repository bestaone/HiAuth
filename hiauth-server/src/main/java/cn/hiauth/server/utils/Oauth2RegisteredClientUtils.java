package cn.hiauth.server.utils;

import cn.hiauth.server.entity.Oauth2RegisteredClient;

import java.time.LocalDateTime;
import java.util.UUID;

public class Oauth2RegisteredClientUtils {

    public final static String CLIENT_SETTINGS = """
            {"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-authorization-consent":true,"settings.client.require-proof-key":false,"cid":"${CID}","appId":"${APPID}"}
            """;
    public final static String TOKEN_SETTINGS = """
            {"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":true,"settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],"settings.token.access-token-time-to-live":["java.time.Duration",${ACCESS_TOKEN_TIME_TO_LIVE}],"settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat","value":"self-contained"},"settings.token.refresh-token-time-to-live":["java.time.Duration",2592000],"settings.token.authorization-code-time-to-live":["java.time.Duration",600],"settings.token.device-code-time-to-live":["java.time.Duration",300]}
            """;

    public static final String ACCESS_TOKEN_TIME_TO_LIVE = "36000";

    public static Oauth2RegisteredClient getDefaultClient(Long cid, Long appId, String clientName, String clientSecret) {

        String clientId = UUID.randomUUID().toString().replace("-", "");
        String clientSettings = CLIENT_SETTINGS.replace("${CID}", cid + "").replace("${APPID}", appId + "");
        String tokenSettings = TOKEN_SETTINGS.replace("${ACCESS_TOKEN_TIME_TO_LIVE}", ACCESS_TOKEN_TIME_TO_LIVE);

        Oauth2RegisteredClient o = new Oauth2RegisteredClient();
        o.setCid(cid);
        o.setAppId(appId);
        o.setClientId(clientId);
        o.setClientIdIssuedAt(LocalDateTime.now());
        o.setClientSecret(clientSecret);
        o.setClientName(clientName);
        o.setClientAuthenticationMethods("client_secret_basic,client_secret_post");
        o.setAuthorizationGrantTypes("refresh_token,authorization_code,client_credentials,password");
        o.setScopes("openid,profile,user");
        o.setClientSettings(clientSettings);
        o.setTokenSettings(tokenSettings);

        return o;
    }

    public static String getClientSettings(Long cid, Long appId) {
        return CLIENT_SETTINGS.replace("${CID}", cid + "").replace("${APPID}", appId + "");
    }

    public static String getTokenSettings(Integer accessTokenTimeToLive) {
        return TOKEN_SETTINGS.replace("${ACCESS_TOKEN_TIME_TO_LIVE}", accessTokenTimeToLive + "");
    }

}
