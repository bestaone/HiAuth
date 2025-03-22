package cn.hiauth.server.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

@Data
@Configuration
public class AppProperties implements Serializable {

    @Value(value = "${loginPage.title:统一认证中心}")
    private String loginPageTitle;

    @Value(value = "${loginPage.path:login}")
    private String loginPagePath;

    @Value(value = "${loginPage.username:}")
    private String loginPageUsername;

    @Value(value = "${loginPage.password:}")
    private String loginPagePassword;

    @Value(value = "${loginPage.usernamePlaceholder:用户名}")
    private String loginPageUsernamePlaceholder;

    @Value(value = "${loginPage.passwordPlaceholder:密码}")
    private String loginPagePasswordPlaceholder;

    /**
     * 公钥
     */
    @Value("${scms.security.publicKey:}")
    private String publicKey;

    /**
     * 加密类型 NONE、RSA
     */
    @Value("${scms.security.encryptType:NONE}")
    private String encryptType;

}
