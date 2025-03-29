package cn.hiauth.server.api.dto.appClient;

import cn.webestar.scms.commons.api.LimitBody;
import cn.webestar.scms.commons.entity.Entity;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AppClientLimitDto extends LimitBody {

    @Schema(description = "租户id")
    private Long cid;

    @Schema(description = "名称")
    private String name;

    @Override
    public <T extends Entity> LambdaQueryWrapper<T> toQueryWapper() {
        return null;
    }

}
