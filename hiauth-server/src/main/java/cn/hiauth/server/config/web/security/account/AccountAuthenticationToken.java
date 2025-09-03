package cn.hiauth.server.config.web.security.account;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class AccountAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = 1L;
    private final Object principal;
    private String formToken;
    private String clientId;
    private String password;
    private String captcha;

    public AccountAuthenticationToken(String formToken, String clientId, String username, String password, String captcha) {
        super(null);
        this.formToken = formToken;
        this.clientId = clientId;
        this.principal = username;
        this.password = password;
        this.captcha = captcha;
        setAuthenticated(false);
    }

    public AccountAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return password;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    public String getFormToken() {
        return formToken;
    }

    public String getCaptcha() {
        return captcha;
    }

    public String getClientId() {
        return clientId;
    }

}
