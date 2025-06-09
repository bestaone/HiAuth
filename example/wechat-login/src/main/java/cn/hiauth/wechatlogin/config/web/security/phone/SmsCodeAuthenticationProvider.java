package cn.hiauth.wechatlogin.config.web.security.phone;

import cn.hiauth.wechatlogin.service.CustomUserDetailsService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

    private final CustomUserDetailsService userDetailsService;

    public SmsCodeAuthenticationProvider(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken) authentication;

        String mobile = (String) authenticationToken.getPrincipal();
        String code = (String) authenticationToken.getCredentials();

        // 这里应该添加验证码校验逻辑，验证手机号和验证码是否匹配
        // 示例中简化处理，实际应用中应该调用短信验证服务验证code是否正确

        UserDetails userDetails = userDetailsService.loadUserByMobile(mobile);
        if (userDetails == null) {
            throw new BadCredentialsException("手机号未注册");
        }

        // 验证通过后，返回认证成功的令牌
        SmsCodeAuthenticationToken token = new SmsCodeAuthenticationToken(userDetails, userDetails.getAuthorities());
        token.setDetails(authenticationToken.getDetails());

        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
