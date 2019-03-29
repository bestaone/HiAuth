package com.bestaone.hiauth.config.smscode;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SmsCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private static final String DEFAULT_PARAMETER_NAME_MOBILE = "telNo";
    private static final String DEFAULT_PARAMETER_NAME_SMSCODE = "smsCode";

    private boolean postOnly = true;

    /**
     * @param defaultFilterProcessesUrl 要拦截的url
     * @param failureUrl
     */
    public SmsCodeAuthenticationFilter(String defaultFilterProcessesUrl, String failureUrl) {
        super(new AntPathRequestMatcher(defaultFilterProcessesUrl, HttpMethod.POST.name()));
        setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler(failureUrl));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (postOnly && !request.getMethod().equals(HttpMethod.POST.name())) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        String mobile = obtainMobile(request);
        String smsCode = obtainSmsCode(request);
        if (mobile == null) {
            mobile = "";
        }
        mobile = mobile.trim();
        //创建SmsCodeAuthenticationToken(未认证)
        SmsCodeAuthenticationToken authRequest = new SmsCodeAuthenticationToken(mobile, smsCode);
        //设置用户信息
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    protected String obtainMobile(HttpServletRequest request) {
        return request.getParameter(DEFAULT_PARAMETER_NAME_MOBILE);
    }

    protected String obtainSmsCode(HttpServletRequest request) {
        return request.getParameter(DEFAULT_PARAMETER_NAME_SMSCODE);
    }

    protected void setDetails(HttpServletRequest request, SmsCodeAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

}
