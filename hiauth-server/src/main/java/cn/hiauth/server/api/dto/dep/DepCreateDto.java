package cn.hiauth.server.api.dto.dep;

import cn.hiauth.server.entity.Department;
import cn.webestar.scms.commons.api.CreateBody;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DepCreateDto extends CreateBody {

    @Schema(description = "租户id")
    private Long cid;

    @Schema(description = "父部门")
    private Long pid;

    @Schema(description = "排序号")
    private Integer sort;

    @Schema(description = "部门编码")
    @NotBlank(message = "不能为空")
    @Size(min = 2, max = 20, message = "长度必须在2到20个字符之间")
    private String no;

    @Schema(description = "部门名称")
    @NotBlank(message = "不能为空")
    @Size(min = 2, max = 20, message = "长度必须在2到20个字符之间")
    private String name;

    @Schema(description = "状态，0：禁用，1：启用")
    @NotNull(message = "不能为NULL")
    private Integer status;

    @Schema(description = "备注")
    @Size(max = 100, message = "长度不能超过100")
    private String remark;

    @Override
    public Department toDO() {
        Department o = new Department();
        o.setCid(cid);
        o.setPid(pid);
        o.setSort(sort);
        o.setNo(no);
        o.setName(name);
        o.setStatus(status);
        o.setRemark(remark);
        o.setCreateTime(LocalDateTime.now());
        o.setUpdateTime(LocalDateTime.now());
        return o;
    }

}
