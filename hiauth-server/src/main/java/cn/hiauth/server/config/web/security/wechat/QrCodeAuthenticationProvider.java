package cn.hiauth.server.config.web.security.wechat;

import cn.hiauth.server.config.web.auth.AuthUser;
import cn.hiauth.server.config.web.security.MultiAuthUserService;
import cn.hiauth.server.entity.App;
import cn.hiauth.server.mapper.AppMapper;
import cn.webestar.scms.commons.CommonException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.security.web.savedrequest.SimpleSavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Data
public class QrCodeAuthenticationProvider implements AuthenticationProvider {

    private MultiAuthUserService userDetailsService;

    private HttpSessionRequestCache httpSessionRequestCache;

    private AppMapper appMapper;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        QrCodeAuthenticationToken authenticationToken = (QrCodeAuthenticationToken) authentication;

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        SavedRequest savedRequest = httpSessionRequestCache.getRequest(request, null);

        String loginClientId = authenticationToken.getClientId();
        String clientId = null;
        if (savedRequest != null) {
            String[] clientIds = savedRequest.getParameterValues(OAuth2ParameterNames.CLIENT_ID);
            if (clientIds != null && clientIds.length > 0) {
                clientId = clientIds[0];
            }
        }
        // 如果登录时指定了具体的应用，并且应用和跳转授权指定的应用不一致时，使用登录时指定的应用
        if (StringUtils.hasText(loginClientId) && !loginClientId.equals(clientId)) {
            clientId = loginClientId;
            App app = appMapper.findByClientId(loginClientId);
            if (app!=null && StringUtils.hasText(app.getHome())) {
                savedRequest = new SimpleSavedRequest(app.getHome());
                request.getSession().setAttribute("SPRING_SECURITY_SAVED_REQUEST", savedRequest);
                request.getSession().setAttribute("SPRING_SECURITY_SAVED_REQUEST" + "_" + clientId, savedRequest);
            }
        }

        String code = (String) authenticationToken.getPrincipal();
        AuthUser authUser = null;
        try {
            authUser = userDetailsService.loadUserWeChatCode(clientId, code);
        } catch (CommonException | BadCredentialsException ex) {
            throw new InternalAuthenticationServiceException("登录失败:" + ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException("登录失败:账号异常", ex);
        }
        if (authUser == null) {
            throw new InternalAuthenticationServiceException("登录失败：账号未注册");
        }
        return new UsernamePasswordAuthenticationToken(authUser, authUser.getPassword(), authUser.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return QrCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
