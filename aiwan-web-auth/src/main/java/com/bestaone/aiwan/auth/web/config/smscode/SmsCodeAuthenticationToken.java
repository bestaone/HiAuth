package com.bestaone.aiwan.auth.web.config.smscode;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class SmsCodeAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = 2383092775910246006L;

    private final String mobile;
    private String smsCode;

    public SmsCodeAuthenticationToken(String mobile, String smsCode) {
        super(null);
        this.mobile = mobile;
        this.smsCode = smsCode;
        setAuthenticated(false);
    }

    public SmsCodeAuthenticationToken(String mobile, String smsCode, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.mobile = mobile;
        this.smsCode = smsCode;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return this.smsCode;
    }

    @Override
    public Object getPrincipal() {
        return this.mobile;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }
        super.setAuthenticated(false);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        this.smsCode = null;
    }

}
