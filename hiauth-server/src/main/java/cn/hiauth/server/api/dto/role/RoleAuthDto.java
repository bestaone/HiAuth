package cn.hiauth.server.api.dto.role;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;

@Data
public class RoleAuthDto {

    @Schema(description = "应用id")
    @NotNull(message = "不能为NULL")
    private Long appId;

    @Schema(description = "角色id")
    @NotNull(message = "不能为NULL")
    private Long roleId;

    @Schema(description = "资源id")
    private Set<Long> appResourceIds;

}
