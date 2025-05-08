package cn.hiauth.server.service;

import cn.hiauth.server.config.rest.security.MySecurityUser;
import cn.hiauth.server.entity.Corp;
import cn.hiauth.server.entity.Employee;
import cn.hiauth.server.entity.User;
import cn.hiauth.server.mapper.CorpMapper;
import cn.hiauth.server.mapper.EmployeeMapper;
import cn.webestar.scms.commons.Assert;
import cn.webestar.scms.security.SecurityCorp;
import cn.webestar.scms.security.SecurityService;
import cn.webestar.scms.security.SecurityUser;
import cn.webestar.scms.security.SessionContextHolder;
import cn.webestar.scms.security.api.AccountLoginDto;
import cn.webestar.scms.security.api.PhoneNumLoginDto;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
public class SimpleSecurityService implements SecurityService {

    @Autowired
    private UserService userService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CorpMapper corpMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

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
    public String sendSmsCode(Integer type, String phoneNum, String smsCode) {
        log.debug("生成短信验证码:{}, 手机号:{}, 有效期:{}秒", smsCode, phoneNum, 300);
        return smsCode;
    }

    @Override
    public List<SecurityCorp> loadUserCorps(Long userId) {
        List<Corp> corps = corpMapper.findByUserId(userId);
        Assert.notNull(corps, "未加入任何组织");
        return corps.stream().map(corp -> new SecurityCorp(corp.getId(), corp.getName())).toList();
    }

    @Override
    public Boolean switchCorp(Long id) {
        SecurityUser securityUser = SessionContextHolder.getContext().getAuth().getPrincipal();
        // 查询员工
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getCid, id);
        queryWrapper.eq(Employee::getUserId, securityUser.getUserId());
        Employee emp = employeeService.getOne(queryWrapper);
        Assert.notNull(emp, "切换失败");
        // 更新session
        securityUser.setCid(id);
        securityUser.setEmpId(emp.getId());
        SessionContextHolder.refresh();
        // 更新登录时间
        emp.setLastLoginTime(LocalDateTime.now());
        employeeService.updateById(emp);
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
