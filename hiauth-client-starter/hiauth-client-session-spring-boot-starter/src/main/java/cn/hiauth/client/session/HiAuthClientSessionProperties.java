package cn.hiauth.client.session;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;
import java.util.Set;

@Data
@ConfigurationProperties("hiauth.client")
public class HiAuthClientSessionProperties implements Serializable {

    /**
     * 缓存前缀
     */
    private String cachePrefix = "hiauth";

    /**
     * 无需登录也无需鉴权的接口(暂时未启用)
     */
    private Set<String> ignoreUris = Set.of("/unpapi/**");

    /**
     * 需要登录，并且需要拥有权限，才可访问的接口
     */
    private Set<String> authUris = Set.of("/api/**");

}
