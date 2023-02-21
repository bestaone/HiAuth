package cn.hiauth.auth.config.multilogin;

import cn.hiauth.auth.utils.CacheUtil;
import cn.hiauth.auth.utils.Constant;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class MultiAuthenticationProvider extends DaoAuthenticationProvider {

    @Autowired
    private CacheUtil cacheUtil;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String loginType = request.getParameter("loginType");
        if("pwd".equals(loginType)) {
            checkPwdLogin(userDetails, authentication);
        } else if("sms".equals(loginType)) {
            checkSmsLogin(request, authentication);
        } else {
            this.logger.debug("不支持的登录方式: " + loginType);
            throw new BadCredentialsException("不支持的登录方式: " + loginType);
        }
    }

    private void checkPwdLogin(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication){
        if (authentication.getCredentials() == null) {
            this.logger.debug("Failed to authenticate since no credentials provided");
            throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
        }
        String presentedPassword = authentication.getCredentials().toString();
        if (!this.getPasswordEncoder().matches(presentedPassword, userDetails.getPassword())) {
            this.logger.debug("Failed to authenticate since password does not match stored value");
            throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
        }
    }

    private void checkSmsLogin(HttpServletRequest request, UsernamePasswordAuthenticationToken authentication){
        //此处写验证短信码
        String smsCodeParam = request.getParameter("smsCode");
        String key = Constant.CACHE_KEY_SMS_YZM + ":" + authentication.getPrincipal();
        Integer smsCode = (Integer) cacheUtil.get(key);
        if(smsCode==null || !smsCode.toString().equals(smsCodeParam)){
            throw new BadCredentialsException("短信验证码错误,请重新输入");
        }
    }



}
