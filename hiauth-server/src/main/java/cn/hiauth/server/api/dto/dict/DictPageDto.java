package cn.hiauth.server.api.dto.dict;

import cn.hiauth.server.entity.Dict;
import cn.webestar.scms.commons.api.PageBody;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class DictPageDto extends PageBody {

    @Schema(description = "关键字")
    private String keyword;

    @Schema(description = "租户ID")
    private Long cid;

    @Schema(description = "员工号")
    private String code;

    @Schema(description = "父编码")
    private String pCode;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "根节点")
    private Boolean isRoot;

    @Override
    public LambdaQueryWrapper<Dict> toQueryWapper() {
        LambdaQueryWrapper<Dict> queryWrapper = new LambdaQueryWrapper<>();
        if (cid != null) {
            queryWrapper.eq(Dict::getCid, cid);
        }
        if (StringUtils.hasText(pCode)) {
            // 通过父编码查询字典值
            queryWrapper.like(Dict::getPCode, pCode);
        } else if (isRoot) {
            // 查询字典分类
            queryWrapper.isNull(Dict::getPCode);
        }
        if (StringUtils.hasText(keyword)) {
            queryWrapper.and(wrapper -> wrapper.like(Dict::getName, keyword)
                    .or().like(Dict::getCode, keyword)
                    .or().like(Dict::getPCode, keyword));
        }
        if (StringUtils.hasText(code)) {
            queryWrapper.like(Dict::getCode, code);
        }
        if (StringUtils.hasText(name)) {
            queryWrapper.like(Dict::getName, name);
        }
        return queryWrapper;
    }

}
