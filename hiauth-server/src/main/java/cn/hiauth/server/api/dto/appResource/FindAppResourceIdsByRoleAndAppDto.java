package cn.hiauth.server.api.dto.appResource;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FindAppResourceIdsByRoleAndAppDto {

    @Schema(description = "appId")
    @NotNull(message = "不能为NULL")
    private Long appId;

    @Schema(description = "roleId")
    @NotNull(message = "不能为NULL")
    private Long roleId;

}
