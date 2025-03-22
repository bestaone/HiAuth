package cn.hiauth.server.config.web.security;

import cn.hiauth.server.config.web.auth.AuthUser;
import cn.hiauth.server.utils.Constant;
import cn.webestar.scms.cache.CacheUtil;
import cn.webestar.scms.commons.CommonException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
public class MultiAuthenticationProvider implements AuthenticationProvider {

    private CacheUtil cacheUtil;

    private PasswordEncoder passwordEncoder;

    private MultiAuthUserService multiAuthUserService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String loginType = request.getParameter("loginType");
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
            if ("pwd".equals(loginType)) {
                String username = (String) authentication.getPrincipal();
                authUser = multiAuthUserService.loadUserByUsername(clientId, username);
                checkPwdLogin(authUser, authentication);
            } else if ("sms".equals(loginType)) {
                String phoneNum = (String) authentication.getPrincipal();
                authUser = multiAuthUserService.loadUserByPhoneNum(clientId, phoneNum);
                checkSmsLogin(request, authentication);
            } else {
                log.debug("不支持的登录方式: " + loginType);
                throw new BadCredentialsException("不支持的登录方式: " + loginType);
            }
        } catch (CommonException ex) {
            throw new InternalAuthenticationServiceException("登录失败:" + ex.getMessage(), ex);
        } catch (UsernameNotFoundException | InternalAuthenticationServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException("登录失败，用户名或者密码错误", ex);
        }

        if (authUser == null) {
            throw new InternalAuthenticationServiceException("UserDetailsService returned null, which is an interface contract violation");
        }

        return new UsernamePasswordAuthenticationToken(authUser, authUser.getPassword(), authUser.getAuthorities());
    }

    private void checkPwdLogin(AuthUser authUser, Authentication authentication) {
        if (authentication.getCredentials() == null) {
            log.debug("Failed to authenticate since no credentials provided");
            throw new BadCredentialsException("AbstractUserDetailsAuthenticationProvider.badCredentials Bad credentials");
        }
        String presentedPassword = authentication.getCredentials().toString();
        if (!this.passwordEncoder.matches(presentedPassword, authUser.getPassword())) {
            log.debug("Failed to authenticate since password does not match stored value");
            throw new BadCredentialsException("AbstractUserDetailsAuthenticationProvider.badCredentials Bad credentials");
        }
    }

    private void checkSmsLogin(HttpServletRequest request, Authentication authentication) {
        //此处写验证短信码
        String smsCodeParam = request.getParameter("smsCode");
        String key = Constant.CACHE_KEY_SMS_CODE + ":" + authentication.getPrincipal();
        Integer smsCode = (Integer) cacheUtil.get(key);
        if (smsCode == null || !smsCode.toString().equals(smsCodeParam)) {
            throw new BadCredentialsException("短信验证码错误,请重新输入");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

    public void setCacheUtil(CacheUtil cacheUtil) {
        this.cacheUtil = cacheUtil;
    }

    public void setMultiAuthUserService(MultiAuthUserService multiAuthUserService) {
        this.multiAuthUserService = multiAuthUserService;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

}
