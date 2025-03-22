package cn.hiauth.server.api.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "UserPwdUpdateDto")
public class UserPwdUpdateDto {

    @Schema(description = "用户id")
    @NotNull(message = "不能为NULL")
    private Long userId;

    @NotBlank(message = "不能为空")
    @Size(min = 5, max = 20, message = "长度必须在5~20之间")
    @Schema(description = "pwd")
    private String pwd;

    @NotBlank(message = "不能为空")
    @Size(min = 5, max = 20, message = "长度必须在5~20之间")
    @Schema(description = "rawPwd")
    private String rawPwd;

}
