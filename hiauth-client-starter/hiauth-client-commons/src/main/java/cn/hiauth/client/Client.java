package cn.hiauth.client;

import lombok.Data;

@Data
public class Client {

    private String clientId;
    private String clientSecret;
    private String[] scope;
    private String redirectUri;
    /**
     * TODO 登录成功后会将accessToken通过这个地址返回给前端
     * 这里需优化，accessToken不能直接给到前端，存在安全隐患
     */
    private String authSuccessRedirectUri;
    private Boolean checkPermission;
    private String cachePrefix;
    private Integer cacheExpire = 60 * 60 * 5;

}
