package cn.hiauth.server.api.dto.dep;

import cn.hiauth.server.entity.Department;
import cn.webestar.scms.commons.api.LimitBody;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class DepLimitDto extends LimitBody {

    @Schema(description = "租户id")
    private Long cid;

    @Schema(description = "关键字")
    private String keyword;

    @Override
    public LambdaQueryWrapper<Department> toQueryWapper() {
        LambdaQueryWrapper<Department> queryWrapper = new LambdaQueryWrapper<>();
        if (cid != null) {
            queryWrapper.eq(Department::getCid, cid);
        }
        if (StringUtils.hasText(keyword)) {
            queryWrapper.and(wrapper -> wrapper.like(Department::getNo, keyword).or().like(Department::getName, keyword));
        }
        queryWrapper.last("OFFSET " + this.getOffset() + " LIMIT " + this.getLimit());
        return queryWrapper;
    }

}
