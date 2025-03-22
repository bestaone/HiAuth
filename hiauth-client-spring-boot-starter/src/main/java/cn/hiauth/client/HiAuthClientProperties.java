package cn.hiauth.client;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;
import java.util.Set;

@Data
@ConfigurationProperties("hiauth.client")
public class HiAuthClientProperties implements Serializable {

    /**
     * 认证完成后跳转的页面
     */
    private String authSuccessRedirectUri;

    /**
     * 缓存前缀
     */
    private String cachePrefix = "auth";

    /**
     * 是否开启检查权限
     */
    private boolean checkPermission = true;

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

}
