package cn.hiauth.server.api.dto.emp;

import cn.hiauth.server.entity.Employee;
import cn.webestar.scms.commons.api.PageBody;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.Set;

@Data
public class EmpPageDto extends PageBody {

    @Schema(description = "租户ID")
    private Long cid;

    @Schema(description = "部门id")
    private Set<Long> depIds;

    @Schema(description = "关键字")
    private String keyword;

    @Schema(description = "员工号")
    private String no;

    @Schema(description = "姓名")
    private String name;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "是否为租户管理员")
    private Boolean isCorpAdmin;

    @Override
    public LambdaQueryWrapper<Employee> toQueryWapper() {
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getIsDeleted, false);
        if (cid != null) {
            queryWrapper.eq(Employee::getCid, cid);
        }
        if (StringUtils.hasText(keyword)) {
            queryWrapper.and(wrapper -> wrapper.like(Employee::getNo, keyword)
                    .or().like(Employee::getName, keyword)
                    .or().like(Employee::getEmail, keyword));
        }
        if (StringUtils.hasText(no)) {
            queryWrapper.like(Employee::getNo, no);
        }
        if (StringUtils.hasText(name)) {
            queryWrapper.like(Employee::getName, name);
        }
        if (StringUtils.hasText(email)) {
            queryWrapper.like(Employee::getEmail, email);
        }
        if (isCorpAdmin != null) {
            queryWrapper.eq(Employee::getIsCorpAdmin, isCorpAdmin);
        }
        return queryWrapper;
    }

}
