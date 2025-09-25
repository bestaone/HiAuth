package cn.hiauth.server.config.web.security.account;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;

public class AccountAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public AccountAuthenticationFilter(String processUrl) {
        super(new AntPathRequestMatcher(processUrl, HttpMethod.POST.name()));
    }

    /**
     * 获取登录表单中提交的参数（formToken、clientId...），并创建authenticationToken，进行认证
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, ServletException, IOException {
        String method = request.getMethod();
        if (!HttpMethod.POST.name().equalsIgnoreCase(method)) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            String formToken = request.getParameter("formToken");
            String clientId = request.getParameter("clientId");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String captcha = request.getParameter("captcha");
            AccountAuthenticationToken authenticationToken = new AccountAuthenticationToken(formToken, clientId, username, password, captcha);
            return this.getAuthenticationManager().authenticate(authenticationToken);
        }
    }

}