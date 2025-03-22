package cn.hiauth.server.api.dto.corp;

import cn.hiauth.server.entity.Corp;
import cn.webestar.scms.commons.api.PageBody;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class CorpPageDto extends PageBody {

    @Schema(description = "名称")
    private String name;

    @Schema(description = "状态，0：禁用，1：启用")
    private Integer status;

    @Override
    public LambdaQueryWrapper<Corp> toQueryWapper() {
        LambdaQueryWrapper<Corp> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(name)) {
            queryWrapper.like(Corp::getName, name);
        }
        if (status != null) {
            queryWrapper.eq(Corp::getStatus, status);
        }
        return queryWrapper;
    }

}
