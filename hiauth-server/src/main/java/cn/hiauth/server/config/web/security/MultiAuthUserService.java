package cn.hiauth.server.config.web.security;

import cn.hiauth.server.config.web.auth.AuthGrantedAuthority;
import cn.hiauth.server.config.web.auth.AuthUser;
import cn.hiauth.server.entity.*;
import cn.hiauth.server.mapper.UserMapper;
import cn.hiauth.server.service.AppResourceService;
import cn.hiauth.server.service.EmployeeService;
import cn.hiauth.server.service.Oauth2RegisteredClientService;
import cn.hiauth.server.service.RoleService;
import cn.webestar.scms.commons.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MultiAuthUserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private EmployeeService employeeService;

    @Resource
    private RoleService roleService;

    @Resource
    private AppResourceService appResourceService;

    @Resource
    private Oauth2RegisteredClientService oauth2RegisteredClientService;

    public AuthUser loadUserByUsername(String clientId, String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        User user = userMapper.selectOne(queryWrapper);
        Assert.notNull(user, "用户名或者密码错误");
        Oauth2RegisteredClient client = oauth2RegisteredClientService.findByClientId(clientId);
        return loadAuthUser(client, user);
    }

    public AuthUser loadUserByPhoneNum(String clientId, String phoneNum) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getPhoneNum, phoneNum);
        User user = userMapper.selectOne(queryWrapper);
        Assert.notNull(user, "用户名或者密码错误");
        Oauth2RegisteredClient client = oauth2RegisteredClientService.findByClientId(clientId);
        return loadAuthUser(client, user);
    }

    public AuthUser loadUserByUserId(String registeredClientId, Long userId) {
        User user = userMapper.selectById(userId);
        Assert.notNull(user, "用户名或者密码错误");
        Oauth2RegisteredClient client = oauth2RegisteredClientService.getById(registeredClientId);
        Assert.notNull(client, "用户名或者密码错误");
        return loadAuthUser(client, user);
    }

    //TODO 当clientId不为空的时候，判断emp是否存在，如果不存在，抛出
    public AuthUser loadAuthUser(Oauth2RegisteredClient client, User user) {

        Employee employee = null;
        Set<AuthGrantedAuthority> authorities = new HashSet<>();

        if (client != null && client.getCid() != null) {
            LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Employee::getCid, client.getCid());
            queryWrapper.eq(Employee::getIsDeleted, false);
            queryWrapper.eq(Employee::getUserId, user.getId());
            employee = employeeService.getOne(queryWrapper);
            Assert.notNull(employee, String.format("不是应用%s的用户", client.getClientName()));
            List<Role> roles = roleService.findByEmpId(employee.getId());
            Set<Long> roleIds = new HashSet<>();
            roles.forEach(i -> roleIds.add(i.getId()));
            if (client.getAppId() != null && !roleIds.isEmpty()) {
                List<AppResource> resources = appResourceService.findByAppIdAndRoleIds(client.getAppId(), roleIds);
                resources.forEach(i -> {
                    AuthGrantedAuthority aga = new AuthGrantedAuthority(i.getCode());
                    aga.setPage(i.getUrl());
                    aga.setApi(i.getApi());
                    authorities.add(aga);
                });
            }
        }

        return new AuthUser(client, user, employee, authorities);
    }

}
