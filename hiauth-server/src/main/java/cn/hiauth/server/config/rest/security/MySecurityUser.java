package cn.hiauth.server.config.rest.security;

import cn.webestar.scms.security.SecurityUser;
import lombok.Data;

@Data
public class MySecurityUser extends SecurityUser {

    private Boolean isCorpAdmin;
    private Boolean isSysAdmin;

}
