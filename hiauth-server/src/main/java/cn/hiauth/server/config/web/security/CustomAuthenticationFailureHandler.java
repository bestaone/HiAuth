package cn.hiauth.server.config.web.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;

/**
 * 认证失败处理器，主要作用是在失败的页面url后缀中添加参数client_id
 */
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private String defaultFailureUrl;

    public CustomAuthenticationFailureHandler(String failureUrl) {
        super(failureUrl);
        this.defaultFailureUrl = failureUrl;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String clientId = request.getParameter(MultiAppHttpSessionRequestCache.CLIENT_ID_KEY);
        String failureUrl = defaultFailureUrl;
        if (StringUtils.hasText(clientId)) {
            URI uri = URI.create(defaultFailureUrl);
            failureUrl = UriComponentsBuilder.fromUri(uri).queryParam(OAuth2ParameterNames.CLIENT_ID, clientId).toUriString();
        }
        saveException(request, exception);
        getRedirectStrategy().sendRedirect(request, response, failureUrl);
    }

}
