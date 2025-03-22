package cn.hiauth.server.api.dto;

import cn.hiauth.server.entity.Role;
import cn.webestar.scms.commons.api.PageBody;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.StringUtils;

@Data
@EqualsAndHashCode(callSuper = true)
public class PageRoleDto extends PageBody {

    private String keyword;

    @Override
    public LambdaQueryWrapper<Role> toQueryWapper() {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(keyword)) {
            queryWrapper.like(Role::getName, keyword);
        }
        return queryWrapper;
    }

}
