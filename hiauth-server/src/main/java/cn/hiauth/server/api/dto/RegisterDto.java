package cn.hiauth.server.api.dto;

import cn.hiauth.server.entity.Corp;
import cn.hiauth.server.entity.User;
import cn.hiauth.server.utils.Constant;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegisterDto {

    @Size(min = 2, max = 20, message = "长度必须在2到30个字符之间")
    @Schema(description = "租户名称")
    private String corpName;

    @Size(min = 5, max = 20, message = "长度必须在2到20个字符之间")
    @Schema(description = "用户名")
    private String username;

    @Size(min = 5, max = 20, message = "长度必须在5到20个字符之间")
    @Schema(description = "密码")
    private String password;

    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号码格式不正确")
    @Schema(description = "手机号码")
    private String phoneNum;

    @Size(min = 3, max = 6, message = "长度必须在3到6个字符之间")
    @Schema(description = "短信验证码")
    private String smsCode;

    public Corp toCorp() {
        Corp corp = new Corp();
        corp.setName(corpName);
        corp.setStatus(1);
        return corp;
    }

    public User toUser() {
        User user = new User();
        user.setName(username);
        user.setUsername(username);
        user.setPhoneNum(phoneNum);
        user.setPwd(password);
        user.setAvatarUrl(Constant.USER_DEFAULT_AVATAR);
        user.setIsSysAdmin(false);
        user.setStatus(1);
        user.setRegtime(LocalDateTime.now());
        user.setIsDeleted(false);
        return user;
    }

}
