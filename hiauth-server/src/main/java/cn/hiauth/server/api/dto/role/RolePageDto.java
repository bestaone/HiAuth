package cn.hiauth.server.api.dto.role;

import cn.hiauth.server.entity.Role;
import cn.webestar.scms.commons.api.PageBody;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class RolePageDto extends PageBody {

    @Schema(description = "cid")
    private Long cid;

    @Schema(description = "depId")
    private Long depId;

    @Schema(description = "名称")
    private String name;

    @Override
    public LambdaQueryWrapper<Role> toQueryWapper() {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        if (cid != null) {
            queryWrapper.eq(Role::getCid, cid);
        }
        if (StringUtils.hasText(name)) {
            queryWrapper.like(Role::getName, name);
        }
        return queryWrapper;
    }

}
