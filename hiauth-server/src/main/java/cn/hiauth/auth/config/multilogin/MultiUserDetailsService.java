package cn.hiauth.auth.config.multilogin;

import cn.hiauth.auth.domain.User;
import cn.hiauth.auth.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MultiUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    public MultiUserDetailsService(UserRepository repository){
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        User user = repository.findOneByAccount(account);
        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles("USER")
                .build();
        return userDetails;
    }

}