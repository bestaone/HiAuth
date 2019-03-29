package com.bestaone.hiauth.config.smscode;

import com.bestaone.hiauth.utils.Constant;
import com.bestaone.hiauth.utils.RedisUtil;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    private RedisUtil cacheUtil;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken) authentication;

        //此处写验证短信码
        String key = Constant.CACHE_KEY_SMS_YZM + ":" + authenticationToken.getPrincipal();
        Integer smsCode = (Integer) cacheUtil.get(key);
        if(smsCode==null || !smsCode.toString().equals(authentication.getCredentials())){
            throw new InternalAuthenticationServiceException("短信验证码错误,请重新输入");
        }

        //调用自定义的userDetailsService认证
        UserDetails user = userDetailsService.loadUserByUsername((String) authenticationToken.getPrincipal());
        if (user == null) {
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }

        //销毁短信验证码，以免别人使用次图像验证码刷接口
        cacheUtil.expire(key, 0);

        //重新构建UsernamePasswordAuthenticationToken（已认证）
        UsernamePasswordAuthenticationToken authenticationResult = new UsernamePasswordAuthenticationToken(user, authentication.getCredentials(), user.getAuthorities());
        authenticationResult.setDetails(authenticationToken.getDetails());
        return authenticationResult;
    }

    /**
     * 只有Authentication为SmsCodeAuthenticationToken使用此Provider认证
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public RedisUtil getCacheUtil() {
        return cacheUtil;
    }

    public void setCacheUtil(RedisUtil cacheUtil) {
        this.cacheUtil = cacheUtil;
    }

}
