package cn.hiauth.server.config.web.security.phone;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class SmsCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public SmsCodeAuthenticationFilter(String processUrl, String failureUrl) {
        super(new AntPathRequestMatcher(processUrl, HttpMethod.POST.name()));
        setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler(failureUrl));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String method = request.getMethod();
        if (!HttpMethod.POST.name().equalsIgnoreCase(method)) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            String formToken = request.getParameter("formToken");
            String phone = request.getParameter("phone");
            String smsCode = request.getParameter("smsCode");
            String captcha = request.getParameter("captcha");
            SmsCodeAuthenticationToken authenticationToken = new SmsCodeAuthenticationToken(formToken, phone, smsCode, captcha);
            return this.getAuthenticationManager().authenticate(authenticationToken);
        }
    }

}