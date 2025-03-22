package cn.hiauth.server.config.web.auth;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
public class AuthGrantedAuthority implements GrantedAuthority {

    private String code;
    private String page;
    private String api;

    public AuthGrantedAuthority() {
    }

    public AuthGrantedAuthority(String authority) {
        this.code = authority;
    }

    public AuthGrantedAuthority(String code, String page, String api) {
        this.code = code;
        this.page = page;
        this.api = api;
    }

    @Override
    public String getAuthority() {
        return code;
    }

}
