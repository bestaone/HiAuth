package cn.hiauth.server.config.web.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * 自定义登录页面，主要是在登录页的URL中添加client_id参数，以便收藏下次登录时自动跳回对应的应用
 */
public class CustomLoginUrlAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {

    public CustomLoginUrlAuthenticationEntryPoint(String loginFormUrl) {
        super(loginFormUrl);
    }

    /**
     * 在对地方法应用授权时，将client_id从Request提取出来，追加到url中，以便于用户收藏此登录页。
     * 而且用户使用此收藏的地址登录时，会自动带上client_id参数，从而跳转到对应应用。
     */
    @Override
    protected String determineUrlToUseForThisRequest(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
        String url = super.determineUrlToUseForThisRequest(request, response, exception);
        String clientId = request.getParameter(OAuth2ParameterNames.CLIENT_ID);
        if (!StringUtils.hasText(clientId)) {
            clientId = request.getParameter(MultiAppHttpSessionRequestCache.CLIENT_ID_KEY);
        }
        DefaultSavedRequest savedRequest = (DefaultSavedRequest) request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");
        if (savedRequest != null) {
            String[] clientIds = savedRequest.getParameterValues(OAuth2ParameterNames.CLIENT_ID);
            if (clientIds != null && clientIds.length > 0) {
                clientId = clientIds[0];
            }
        }
        if (!StringUtils.hasText(clientId)) {
            return url;
        }
        URI uri = URI.create(url);
        return UriComponentsBuilder.fromUri(uri).queryParam(OAuth2ParameterNames.CLIENT_ID, clientId).toUriString();
    }

}
