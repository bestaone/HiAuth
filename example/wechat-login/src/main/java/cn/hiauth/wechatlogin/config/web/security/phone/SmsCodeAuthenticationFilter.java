package cn.hiauth.wechatlogin.config.web.security.phone;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class SmsCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public SmsCodeAuthenticationFilter() {
        super(new AntPathRequestMatcher("/phone/doLogin", HttpMethod.POST.name()));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String method = request.getMethod();
        if (!HttpMethod.POST.name().equalsIgnoreCase(method)) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            String phone = request.getParameter("phone");
            String code = request.getParameter("code");
            SmsCodeAuthenticationToken authenticationToken = new SmsCodeAuthenticationToken(phone, code);
            return this.getAuthenticationManager().authenticate(authenticationToken);
        }
    }

}