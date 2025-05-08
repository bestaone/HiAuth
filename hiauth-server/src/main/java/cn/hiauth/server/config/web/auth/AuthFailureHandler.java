package cn.hiauth.server.config.web.auth;

import cn.hutool.json.JSONUtil;
import cn.webestar.scms.commons.R;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationCodeRequestAuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class AuthFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        String errorMsg = "UNKNOW";
        if (exception instanceof OAuth2AuthorizationCodeRequestAuthenticationException ex) {
            String description = ex.getError().getDescription();
            if (description != null && description.contains("redirect_uri")) {
                errorMsg = "未授权的回调地址，请检查redirect_uri参数";
            } else {
                errorMsg = ex.getMessage();
            }
        }
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        String json = JSONUtil.toJsonStr(R.fail(50000, errorMsg));
        response.getWriter().write(json);
    }

}