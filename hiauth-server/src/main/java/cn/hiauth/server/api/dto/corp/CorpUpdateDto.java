package cn.hiauth.server.api.dto.corp;

import cn.hiauth.server.entity.Corp;
import cn.webestar.scms.commons.api.UpdateBody;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "CorpUpdateDto")
public class CorpUpdateDto extends UpdateBody {

    @Schema(description = "id")
    @NotNull(message = "不能为NULL")
    private Long id;

    @Schema(description = "名称")
    @Size(min = 2, max = 20, message = "长度必须在2到20个字符之间")
    private String name;

    @Schema(description = "状态，0：禁用，1：启用")
    private Integer status;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public Corp toDO() {
        Corp o = new Corp();
        o.setId(id);
        o.setName(name);
        o.setStatus(status);
        return o;
    }

}
