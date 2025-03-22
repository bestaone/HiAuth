package cn.hiauth.server.service;

import cn.hiauth.server.config.rest.security.MySecurityUser;
import cn.hiauth.server.entity.Employee;
import cn.hiauth.server.entity.User;
import cn.webestar.scms.commons.Assert;
import cn.webestar.scms.security.SecurityService;
import cn.webestar.scms.security.SecurityUser;
import cn.webestar.scms.security.api.AccountLoginDto;
import cn.webestar.scms.security.api.PhoneNumLoginDto;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SimpleSecurityService implements SecurityService {

    @Autowired
    private UserService userService;

    @Autowired
    private EmployeeService employeeService;

    @Override
    public MySecurityUser loadSecurityUser(AccountLoginDto dto) {
        //查找用户
        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, dto.getUsername()));
        return convert(user);
    }

    @Override
    public SecurityUser loadSecurityUser(PhoneNumLoginDto dto) {
        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getPhoneNum, dto.getPhoneNum()));
        return convert(user);
    }

    @Override
    public Boolean sendSmsCode(Integer type, String phoneNum, String smsCode) {
        log.debug("生成短信验证码:{}, 手机号:{}, 有效期:{}秒", smsCode, phoneNum, 300);
        return true;
    }

    private MySecurityUser convert(User user) {
        Assert.notNull(user, 30010, "用户名或者密码错误");
        MySecurityUser securityUser = new MySecurityUser();
        securityUser.setId(user.getId() + "");
        securityUser.setUserId(user.getId());
        securityUser.setName(user.getName());
        securityUser.setUsername(user.getUsername());
        securityUser.setPassword(user.getPwd());
        securityUser.setPhoneNum(user.getPhoneNum());
        securityUser.setAvatar(user.getAvatarUrl());
        securityUser.setIsSysAdmin(user.getIsSysAdmin());
        if (user.getIsSysAdmin() == null || !user.getIsSysAdmin()) {
            Employee emp = employeeService.lastLoginCorpAdminByUserId(user.getId());
            Assert.notNull(emp, 30010, "用户名或者密码错误");
            securityUser.setCid(emp.getCid());
            securityUser.setEmpId(emp.getId());
            securityUser.setIsCorpAdmin(emp.getIsCorpAdmin());
        }
        return securityUser;
    }

}
