package cn.hiauth.server.api.dto.emp;

import cn.hiauth.server.entity.Employee;
import cn.webestar.scms.commons.api.UpdateBody;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class EmpUpdateDto extends UpdateBody {

    @Schema(description = "id")
    @NotNull(message = "不能为NULL")
    private Long id;

    @Schema(description = "用户id")
    private Long userId;

    @Schema(description = "部门id")
    private Set<Long> depIds;

    @Schema(description = "员工号")
    private String no;

    @Schema(description = "姓名")
    @NotBlank(message = "不能为空")
    @Size(min = 2, max = 10, message = "长度必须在2到10个字符之间")
    private String name;

    @Schema(description = "邮箱")
    @Email
    @Size(min = 5, max = 50, message = "长度必须在5到50个字符之间")
    private String email;

    @Schema(description = "是否为租户管理员")
    @NotNull(message = "不能为NULL")
    private Boolean isCorpAdmin;

    @Schema(description = "角色id")
    private Set<Long> roleIds;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public Employee toDO() {
        Employee o = new Employee();
        o.setId(id);
        o.setUserId(userId);
        o.setNo(no);
        o.setName(name);
        o.setEmail(email);
        o.setIsCorpAdmin(isCorpAdmin);
        o.setUpdateTime(LocalDateTime.now());
        return o;
    }


}
