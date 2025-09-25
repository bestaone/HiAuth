package cn.hiauth.server.config.web.security.wechat;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class QrCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public QrCodeAuthenticationFilter(String processUrl) {
        super(new AntPathRequestMatcher(processUrl));
    }

    /**
     * 获取登录表单中提交的两个参数（clientId、code），并创建authenticationToken，进行认证
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String clientId = request.getParameter("clientId");
        String code = request.getParameter("code");
        QrCodeAuthenticationToken authenticationToken = new QrCodeAuthenticationToken(clientId, code);
        return this.getAuthenticationManager().authenticate(authenticationToken);
    }

}