package cn.hiauth.client.api;

import lombok.Data;

@Data
public class UserPwdUpdateDto {

    private String newPwd;
    private String rawPwd;

}
