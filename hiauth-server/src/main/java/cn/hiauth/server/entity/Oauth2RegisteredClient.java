package cn.hiauth.server.entity;

import cn.webestar.scms.commons.entity.BasicDO;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName(value = "oauth2_registered_client", autoResultMap = true)
@Schema(name = "Oauth2RegisteredClient", description = "oauth2客户端")
public class Oauth2RegisteredClient extends BasicDO<String> {

    @TableField("cid")
    @Schema(description = "租户ID(扩展添加字段)")
    private Long cid;

    @TableField("app_id")
    @Schema(description = "应用id(扩展添加字段)")
    private Long appId;

    @TableField("client_id")
    @Schema(description = "")
    private String clientId;

    @TableField("client_id_issued_at")
    @Schema(description = "")
    private LocalDateTime clientIdIssuedAt;

    @TableField("client_secret")
    @Schema(description = "")
    private String clientSecret;

    @TableField("client_secret_expires_at")
    @Schema(description = "")
    private LocalDateTime clientSecretExpiresAt;

    @TableField("client_name")
    @Schema(description = "")
    private String clientName;

    @TableField("client_authentication_methods")
    @Schema(description = "")
    private String clientAuthenticationMethods;

    @TableField("authorization_grant_types")
    @Schema(description = "")
    private String authorizationGrantTypes;

    @TableField("redirect_uris")
    @Schema(description = "")
    private String redirectUris;

    @TableField("post_logout_redirect_uris")
    @Schema(description = "")
    private String postLogoutRedirectUris;

    @TableField("scopes")
    @Schema(description = "")
    private String scopes;

    @TableField("client_settings")
    @Schema(description = "")
    private String clientSettings;

    @TableField("token_settings")
    @Schema(description = "")
    private String tokenSettings;

}