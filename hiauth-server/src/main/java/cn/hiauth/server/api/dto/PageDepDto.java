package cn.hiauth.server.api.dto;

import cn.hiauth.server.entity.Department;
import cn.webestar.scms.commons.api.PageBody;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.StringUtils;

@Data
@EqualsAndHashCode(callSuper = true)
public class PageDepDto extends PageBody {

    private Long pid;

    private String keyword;

    @Override
    public LambdaQueryWrapper<Department> toQueryWapper() {
        LambdaQueryWrapper<Department> queryWrapper = new LambdaQueryWrapper<>();
        if (pid != null) {
            queryWrapper.eq(Department::getPid, pid);
        }
        if (StringUtils.hasText(keyword)) {
            queryWrapper.and(wrapper -> wrapper.like(Department::getNo, keyword).or().like(Department::getName, keyword));
        }
        return queryWrapper;
    }

}
