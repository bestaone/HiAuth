package cn.hiauth.server.api.dto.user;

import cn.hiauth.server.entity.User;
import cn.webestar.scms.commons.api.UpdateBody;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "UserUpdateDto")
public class UserUpdateDto extends UpdateBody {

    @Schema(description = "id")
    @NotNull(message = "不能为NULL")
    private Long id;

    @Schema(description = "名称")
    @NotBlank(message = "不能为空")
    @Size(min = 2, max = 20, message = "长度必须在2到20个字符之间")
    private String name;

    @Schema(description = "头像")
    @Size(max = 200, message = "长度不能超过200")
    private String avatar;

    @Schema(description = "用户名")
    @NotBlank(message = "不能为空")
    @Size(min = 5, max = 20, message = "长度必须在5到20个字符之间")
    private String username;

    @Schema(description = "手机号码")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号码格式不正确")
    private String phoneNum;

    @Schema(description = "性别，0：未知，1：男，2：女")
    @NotNull(message = "不能为NULL")
    private Integer gender;

    @Schema(description = "状态，0：禁用，1：启用")
    @NotNull(message = "不能为NULL")
    private Integer status;

    @Schema(description = "是否为系统管理员")
    @NotNull(message = "不能为NULL")
    private Boolean isSysAdmin;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public User toDO() {
        User o = new User();
        o.setId(id);
        o.setName(name);
        o.setAvatarUrl(avatar);
        o.setUsername(username);
        o.setPhoneNum(phoneNum);
        o.setGender(gender);
        o.setStatus(status);
        o.setIsSysAdmin(isSysAdmin);
        o.setIsDeleted(false);
        return o;
    }

}
