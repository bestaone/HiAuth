package cn.hiauth.server.api.dto.role;

import cn.hiauth.server.entity.Role;
import cn.webestar.scms.commons.api.CreateBody;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RoleCreateDto extends CreateBody {

    @Schema(description = "cid")
    private Long cid;

    @Schema(description = "名称")
    @NotBlank(message = "不能为空")
    @Size(min = 2, max = 20, message = "长度必须在2到20个字符之间")
    private String name;

    @Schema(description = "备注")
    @Size(max = 100, message = "长度不能超过100")
    private String remark;

    @Schema(description = "是否可用")
    @NotNull(message = "不能为NULL")
    private Boolean isEnable;

    @Override
    public Role toDO() {
        Role o = new Role();
        o.setCid(cid);
        o.setName(name);
        o.setRemark(remark);
        o.setIsEnable(isEnable);
        o.setCreateTime(LocalDateTime.now());
        o.setUpdateTime(LocalDateTime.now());
        return o;
    }

}
