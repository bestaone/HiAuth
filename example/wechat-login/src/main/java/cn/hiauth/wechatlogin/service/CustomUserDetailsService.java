package cn.hiauth.wechatlogin.service;

import cn.hiauth.wechatlogin.entity.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

public class CustomUserDetailsService implements UserDetailsService {

    private PasswordEncoder passwordEncoder;

    public CustomUserDetailsService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("admin".equals(username)) {
            return new CustomUserDetails(
                    "admin",
                    passwordEncoder.encode("123456"),
                    true,
                    Collections.singletonList(() -> "ROLE_ADMIN")
            );
        } else if ("user".equals(username)) {
            return new CustomUserDetails(
                    "user",
                    passwordEncoder.encode("123456"),
                    true,
                    Collections.singletonList(() -> "ROLE_USER")
            );
        }
        throw new UsernameNotFoundException("User not found: " + username);
    }

    public UserDetails loadUserByMobile(String mobile) throws UsernameNotFoundException {
        if ("1388888888".equals(mobile)) {
            return new CustomUserDetails(
                    "admin",
                    passwordEncoder.encode("123456"),
                    true,
                    Collections.singletonList(() -> "ROLE_ADMIN")
            );
        }
        throw new UsernameNotFoundException("User not found: " + mobile);
    }

    public UserDetails loadUserWeChatCode(String code) {
        return new CustomUserDetails(
                "admin",
                passwordEncoder.encode("123456"),
                true,
                Collections.singletonList(() -> "ROLE_ADMIN")
        );
    }
}