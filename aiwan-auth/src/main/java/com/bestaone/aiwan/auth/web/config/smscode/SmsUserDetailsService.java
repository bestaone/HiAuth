package com.bestaone.aiwan.auth.web.config.smscode;

import com.bestaone.aiwan.auth.domain.Resource;
import com.bestaone.aiwan.auth.domain.Role;
import com.bestaone.aiwan.auth.service.ResourceService;
import com.bestaone.aiwan.auth.service.RoleService;
import com.bestaone.aiwan.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SmsUserDetailsService implements UserDetailsService {

    @Autowired
    public UserService userService;

    @Autowired
    public RoleService roleService;

    @Autowired
    public ResourceService resourceService;

    @Override
    public UserDetails loadUserByUsername(String tel) throws UsernameNotFoundException {

        com.bestaone.aiwan.auth.domain.User user = userService.findByTel(tel);

        Set<GrantedAuthority> authorities = new HashSet<>();

        List<Role> roles = roleService.findByUserId(user.getId());
        for (Role role : roles){
            authorities.add(new SimpleGrantedAuthority(role.getCode()));
        }

        List<Resource> resources = resourceService.findByUserId(user.getId());
        for (Resource resource : resources){
            authorities.add(new SimpleGrantedAuthority(resource.getCode()));
        }

        return new User(user.getUsername(), user.getPassword(),authorities);
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

}
