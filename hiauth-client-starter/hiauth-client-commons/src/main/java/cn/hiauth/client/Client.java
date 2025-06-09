package cn.hiauth.client;

import lombok.Data;

@Data
public class Client {

    private String clientId;
    private String clientSecret;
    private String[] scope;
    private String redirectUri;
    private String authSuccessRedirectUri;
    private Boolean checkPermission;
    private String cachePrefix;
    private Integer cacheExpire = 60 * 60 * 5;

}
