package cn.hiauth.server.api.dto.dict;

import cn.hiauth.server.entity.Dict;
import cn.webestar.scms.commons.api.LimitBody;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class DictLimitDto extends LimitBody {

    @Schema(description = "租户id")
    private Long cid;

    @Schema(description = "关键字")
    private String keyword;

    @Schema(description = "pCode")
    private String pcode;

    @Override
    public LambdaQueryWrapper<Dict> toQueryWapper() {
        LambdaQueryWrapper<Dict> queryWrapper = new LambdaQueryWrapper<>();
        if (cid != null) {
            queryWrapper.eq(Dict::getCid, cid);
        }
        if (StringUtils.hasText(keyword)) {
            queryWrapper.and(wrapper -> wrapper.like(Dict::getName, keyword)
                    .or().like(Dict::getCode, keyword)
            );
        }
        if (StringUtils.hasText(pcode)) {
            queryWrapper.eq(Dict::getPCode, pcode);
        }
        return queryWrapper;
    }

}
