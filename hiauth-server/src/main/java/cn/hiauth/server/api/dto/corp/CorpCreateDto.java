package cn.hiauth.server.api.dto.corp;

import cn.hiauth.server.entity.Corp;
import cn.webestar.scms.commons.api.CreateBody;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "CorpCreateDto")
public class CorpCreateDto extends CreateBody {

    @Schema(description = "名称")
    @NotBlank(message = "不能为空")
    @Size(min = 2, max = 20, message = "长度必须在2到20个字符之间")
    private String name;

    @Schema(description = "状态，0：禁用，1：启用")
    @NotNull(message = "不能为NULL")
    private Integer status;

    @Override
    public Corp toDO() {
        Corp o = new Corp();
        o.setName(name);
        o.setAppCount(0);
        o.setDepCount(0);
        o.setEmpCount(0);
        o.setStatus(status);
        o.setIsDeleted(false);
        return o;
    }

}
