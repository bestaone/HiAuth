package cn.hiauth.server.config.web.security.wechat;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class QrCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public QrCodeAuthenticationFilter(String processUrl, String failureUrl) {
        super(new AntPathRequestMatcher(processUrl));
        setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler(failureUrl));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String code = request.getParameter("code");
        QrCodeAuthenticationToken authenticationToken = new QrCodeAuthenticationToken(code);
        return this.getAuthenticationManager().authenticate(authenticationToken);
    }

}