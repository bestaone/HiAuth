package cn.hiauth.mgr.config.security;

import cn.hiauth.mgr.domain.User;
import cn.hiauth.mgr.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DefaultUserDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public SimpleUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);
        if(user==null) throw new UsernameNotFoundException("用户名或者密码错误");
        SimpleUserDetails userDetails  = new SimpleUserDetails();
        userDetails.setUserId(user.getId());
        userDetails.setUsername(user.getUsername());
        userDetails.setPassword(user.getPassword());
        userDetails.setPhoneNum(user.getPhoneNum());
        userDetails.setAvatarUrl(user.getAvatarUrl());
        userDetails.setAuthorities(new ArrayList<GrantedAuthority>());
        return userDetails;
    }

}
