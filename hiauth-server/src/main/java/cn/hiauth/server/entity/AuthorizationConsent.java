package cn.hiauth.server.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName(value = "oauth2_authorization_consent", autoResultMap = true)
@Schema(name = "AuthorizationConsent", description = "认证授权")
public class AuthorizationConsent {

    private String registeredClientId;
    private String clientName;
    private String principalName;
    private String authorities;

}

