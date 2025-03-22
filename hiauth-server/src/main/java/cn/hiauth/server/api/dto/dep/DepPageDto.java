package cn.hiauth.server.api.dto.dep;

import cn.hiauth.server.entity.Department;
import cn.webestar.scms.commons.api.PageBody;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class DepPageDto extends PageBody {

    @Schema(description = "租户id")
    private Long cid;

    @Schema(description = "父部门")
    private Long pid;

    @Schema(description = "关键字")
    private String keyword;

    @Schema(description = "部门编码")
    private String no;

    @Schema(description = "部门名称")
    private String name;

    @Schema(description = "状态，0：禁用，1：启用")
    private Integer status;

    @Override
    public LambdaQueryWrapper<Department> toQueryWapper() {
        LambdaQueryWrapper<Department> queryWrapper = new LambdaQueryWrapper<>();
        if (cid != null) {
            queryWrapper.eq(Department::getCid, cid);
        }
        if (pid != null) {
            queryWrapper.eq(Department::getPid, pid);
        }
        if (StringUtils.hasText(keyword)) {
            queryWrapper.and(wrapper -> wrapper.like(Department::getNo, keyword).or().like(Department::getName, keyword));
        }
        if (StringUtils.hasText(no)) {
            queryWrapper.like(Department::getNo, no);
        }
        if (StringUtils.hasText(name)) {
            queryWrapper.like(Department::getName, name);
        }
        if (status != null) {
            queryWrapper.eq(Department::getStatus, status);
        }
        return queryWrapper;
    }

}
