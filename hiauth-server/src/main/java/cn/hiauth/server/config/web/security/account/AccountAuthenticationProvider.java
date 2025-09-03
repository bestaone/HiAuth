package cn.hiauth.server.config.web.security.account;

import cn.hiauth.server.config.web.auth.AuthUser;
import cn.hiauth.server.config.web.security.MultiAuthUserService;
import cn.hiauth.server.entity.App;
import cn.hiauth.server.mapper.AppMapper;
import cn.hiauth.server.utils.Constant;
import cn.webestar.scms.cache.CacheUtil;
import cn.webestar.scms.commons.CommonException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.security.web.savedrequest.SimpleSavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Data
@Slf4j
public class AccountAuthenticationProvider implements AuthenticationProvider {

    private CacheUtil cacheUtil;

    private PasswordEncoder passwordEncoder;

    private MultiAuthUserService userDetailsService;

    private HttpSessionRequestCache httpSessionRequestCache;

    private AppMapper appMapper;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        AccountAuthenticationToken authenticationToken = (AccountAuthenticationToken) authentication;
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
        AuthUser authUser = null;
        try {
            String formToken = authenticationToken.getFormToken();
            String username = (String) authenticationToken.getPrincipal();
            String password = (String) authenticationToken.getCredentials();
            String captcha = authenticationToken.getCaptcha();
            checkCaptcha(formToken, captcha);
            authUser = userDetailsService.loadUserByUsername(clientId, username);
            checkPwdLogin(authUser, password);
        } catch (CommonException | BadCredentialsException ex) {
            throw new InternalAuthenticationServiceException("登录失败:" + ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException("登录失败:账号异常", ex);
        }
        if (authUser == null) {
            throw new InternalAuthenticationServiceException("UserDetailsService returned null, which is an interface contract violation");
        }
        return new UsernamePasswordAuthenticationToken(authUser, authUser.getPassword(), authUser.getAuthorities());
    }

    private void checkPwdLogin(AuthUser authUser, String password) {
        if (password == null) {
            log.debug("Failed to authenticate since no credentials provided");
            throw new BadCredentialsException("用户名或者密码错误");
        }
        if (!this.passwordEncoder.matches(password, authUser.getPassword())) {
            log.debug("Failed to authenticate since password does not match stored value");
            throw new BadCredentialsException("用户名或者密码错误");
        }
    }

    private void checkCaptcha(String formToken, String captcha) {
        String imgCodeKey = Constant.CACHE_KEY_CAPTCHA + ":" + formToken;
        String captchaCache = (String) cacheUtil.get(imgCodeKey);
        if (captchaCache == null) {
            throw new BadCredentialsException("图形验证码错误");
        }
        if (!captchaCache.equalsIgnoreCase(captcha)) {
            throw new BadCredentialsException("图形验证码错误");
        }
        //销毁图形验证码，以免别人使用次图像验证码刷接口
        cacheUtil.expire(imgCodeKey, 0);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return AccountAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
