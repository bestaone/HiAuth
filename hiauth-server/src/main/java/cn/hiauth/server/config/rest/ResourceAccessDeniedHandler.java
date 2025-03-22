package cn.hiauth.server.config.rest;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.server.resource.authentication.AbstractOAuth2TokenAuthenticationToken;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

public class ResourceAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        if (request.getUserPrincipal() instanceof AbstractOAuth2TokenAuthenticationToken<?>) {
            ResourceAuthenticationEntryPoint.printError(response, "权限不足");
        } else {
            ResourceAuthenticationEntryPoint.printError(response, accessDeniedException.getMessage());
        }
    }
}
