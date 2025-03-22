package cn.hiauth.server.entity;

import cn.webestar.scms.commons.entity.Entity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName(value = "oauth2_authorization_consent", autoResultMap = true)
@Schema(name = "Oauth2AuthorizationConsent", description = "oauth2认证授权")
public class Oauth2AuthorizationConsent implements Entity {

    @TableField("registered_client_id")
    @Schema(description = "")
    private String registeredClientId;

    @TableField("principal_name")
    @Schema(description = "")
    private String principalName;

    @TableField("authorities")
    @Schema(description = "")
    private String authorities;

}