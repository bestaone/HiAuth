package cn.hiauth.server.config.web.security.phone;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class SmsCodeAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = 1L;
    private final Object principal;
    private String formToken;
    private String clientId;
    private String smsCode;
    private String captcha;

    public SmsCodeAuthenticationToken(String formToken, String clientId, String phone, String smsCode, String captcha) {
        super(null);
        this.formToken = formToken;
        this.clientId = clientId;
        this.principal = phone;
        this.smsCode = smsCode;
        this.captcha = captcha;
        setAuthenticated(false);
    }

    public SmsCodeAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return smsCode;
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
