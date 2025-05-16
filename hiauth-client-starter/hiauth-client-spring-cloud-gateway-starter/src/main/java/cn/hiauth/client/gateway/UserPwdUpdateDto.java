package cn.hiauth.client.gateway;

import lombok.Data;

@Data
public class UserPwdUpdateDto {

    private String newPwd;
    private String rawPwd;

}
