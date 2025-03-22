package cn.hiauth.server.api.dto.corpApp;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CorpAppDeleteDto {

    @Schema(description = "租户")
    @NotNull(message = "不能为NULL")
    private Long corpId;

    @Schema(description = "应用")
    @NotNull(message = "不能为NULL")
    private Long[] appIds;

}
