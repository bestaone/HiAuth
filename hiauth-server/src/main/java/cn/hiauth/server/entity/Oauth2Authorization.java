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
@TableName(value = "oauth2_authorization", autoResultMap = true)
@Schema(name = "Oauth2Authorization", description = "oauth2认证")
public class Oauth2Authorization extends BasicDO<String> {

    @TableField("registered_client_id")
    @Schema(description = "")
    private String registeredClientId;

    @TableField("principal_name")
    @Schema(description = "")
    private String principalName;

    @TableField("authorization_grant_type")
    @Schema(description = "")
    private String authorizationGrantType;

    @TableField("authorized_scopes")
    @Schema(description = "")
    private String authorizedScopes;

    @TableField("attributes")
    @Schema(description = "")
    private String attributes;

    @TableField("state")
    @Schema(description = "")
    private String state;

    @TableField("authorization_code_value")
    @Schema(description = "")
    private String authorizationCodeValue;

    @TableField("authorization_code_issued_at")
    @Schema(description = "")
    private LocalDateTime authorizationCodeIssuedAt;

    @TableField("authorization_code_expires_at")
    @Schema(description = "")
    private LocalDateTime authorizationCodeExpiresAt;

    @TableField("authorization_code_metadata")
    @Schema(description = "")
    private String authorizationCodeMetadata;

    @TableField("access_token_value")
    @Schema(description = "")
    private String accessTokenValue;

    @TableField("access_token_issued_at")
    @Schema(description = "")
    private LocalDateTime accessTokenIssuedAt;

    @TableField("access_token_expires_at")
    @Schema(description = "")
    private LocalDateTime accessTokenExpiresAt;

    @TableField("access_token_metadata")
    @Schema(description = "")
    private String accessTokenMetadata;

    @TableField("access_token_type")
    @Schema(description = "")
    private String accessTokenType;

    @TableField("access_token_scopes")
    @Schema(description = "")
    private String accessTokenScopes;

    @TableField("oidc_id_token_value")
    @Schema(description = "")
    private String oidcIdTokenValue;

    @TableField("oidc_id_token_issued_at")
    @Schema(description = "")
    private LocalDateTime oidcIdTokenIssuedAt;

    @TableField("oidc_id_token_expires_at")
    @Schema(description = "")
    private LocalDateTime oidcIdTokenExpiresAt;

    @TableField("oidc_id_token_metadata")
    @Schema(description = "")
    private String oidcIdTokenMetadata;

    @TableField("refresh_token_value")
    @Schema(description = "")
    private String refreshTokenValue;

    @TableField("refresh_token_issued_at")
    @Schema(description = "")
    private LocalDateTime refreshTokenIssuedAt;

    @TableField("refresh_token_expires_at")
    @Schema(description = "")
    private LocalDateTime refreshTokenExpiresAt;

    @TableField("refresh_token_metadata")
    @Schema(description = "")
    private String refreshTokenMetadata;

    @TableField("user_code_value")
    @Schema(description = "")
    private String userCodeValue;

    @TableField("user_code_issued_at")
    @Schema(description = "")
    private LocalDateTime userCodeIssuedAt;

    @TableField("user_code_expires_at")
    @Schema(description = "")
    private LocalDateTime userCodeExpiresAt;

    @TableField("user_code_metadata")
    @Schema(description = "")
    private String userCodeMetadata;

    @TableField("device_code_value")
    @Schema(description = "")
    private String deviceCodeValue;

    @TableField("device_code_issued_at")
    @Schema(description = "")
    private LocalDateTime deviceCodeIssuedAt;

    @TableField("device_code_expires_at")
    @Schema(description = "")
    private LocalDateTime deviceCodeExpiresAt;

    @TableField("device_code_metadata")
    @Schema(description = "")
    private String deviceCodeMetadata;

}