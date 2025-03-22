package cn.hiauth.server.api.dto.corpApp;

import cn.hiauth.server.entity.Oauth2RegisteredClient;
import cn.hiauth.server.utils.Oauth2RegisteredClientUtils;
import cn.webestar.scms.commons.api.UpdateBody;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CorpAppUpdateDto extends UpdateBody {

    @Schema(description = "id")
    @NotNull(message = "不能为NULL")
    private String id;

    @Schema(description = "clientId")
    @NotBlank(message = "不能为空")
    @Size(min = 5, max = 20, message = "长度必须在5到20个字符之间")
    private String clientId;

    @Schema(description = "clientSecret")
    @Size(min = 5, max = 20, message = "长度必须在5到20个字符之间")
    private String clientSecret;

    @Schema(description = "redirectUris")
    @Size(max = 500, message = "长度不能超过500个字符")
    private String redirectUris;

    @Schema(description = "scopes")
    @NotBlank(message = "不能为空")
    @Size(min = 2, max = 100, message = "长度必须在2到100个字符之间")
    private String scopes;

    @Schema(description = "accessTokenTimeToLive")
    @NotNull(message = "不能为NULL")
    private Integer accessTokenTimeToLive;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Oauth2RegisteredClient toDO() {
        Oauth2RegisteredClient o = new Oauth2RegisteredClient();
        o.setId(id);
        o.setClientId(clientId);
        o.setClientSecret(clientSecret);
        o.setRedirectUris(redirectUris);
        o.setScopes(scopes);
        if (accessTokenTimeToLive != null) {
            o.setTokenSettings(Oauth2RegisteredClientUtils.getTokenSettings(accessTokenTimeToLive * 3600));
        }
        return o;
    }

}
