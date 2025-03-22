package cn.hiauth.server.api.dto;

import cn.hiauth.server.entity.Employee;
import cn.webestar.scms.commons.api.PageBody;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.StringUtils;

@Data
@EqualsAndHashCode(callSuper = true)
public class PageEmpDto extends PageBody {

    private Long depId;

    private String keyword;

    private Boolean isDeleted = Boolean.FALSE;

    @Override
    public LambdaQueryWrapper<Employee> toQueryWapper() {
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getIsDeleted, isDeleted);
        if (StringUtils.hasText(keyword)) {
            queryWrapper.and(wrapper -> wrapper.like(Employee::getNo, keyword)
                    .or().like(Employee::getName, keyword)
                    .or().like(Employee::getEmail, keyword)
            );
        }
        return queryWrapper;
    }

}
