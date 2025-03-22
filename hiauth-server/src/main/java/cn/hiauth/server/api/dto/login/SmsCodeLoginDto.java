package cn.hiauth.server.api.dto.login;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SmsCodeLoginDto {

    @NotBlank(message = "不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号码格式不正确")
    private String phoneNum;

    @NotBlank(message = "不能为空")
    @Size(min = 3, max = 6, message = "长度必须在3到6个字符之间")
    private String smsCode;

}
