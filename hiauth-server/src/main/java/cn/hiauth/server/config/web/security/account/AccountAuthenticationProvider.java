package cn.hiauth.server.config.web.security.account;

import cn.hiauth.server.config.web.auth.AuthUser;
import cn.hiauth.server.config.web.security.MultiAuthUserService;
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
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Data
@Slf4j
public class AccountAuthenticationProvider implements AuthenticationProvider {

    private CacheUtil cacheUtil;

    private PasswordEncoder passwordEncoder;

    private MultiAuthUserService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        AccountAuthenticationToken authenticationToken = (AccountAuthenticationToken) authentication;
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        DefaultSavedRequest savedRequest = (DefaultSavedRequest) request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");
        String clientId = null;
        if (savedRequest != null) {
            String[] clientIds = savedRequest.getParameterValues("client_id");
            if (clientIds != null && clientIds.length > 0) {
                clientId = clientIds[0];
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
