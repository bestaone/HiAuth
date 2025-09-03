package cn.hiauth.server.config.web.security;

import cn.hiauth.server.config.props.WechatProperties;
import cn.hiauth.server.config.web.auth.AuthGrantedAuthority;
import cn.hiauth.server.config.web.auth.AuthUser;
import cn.hiauth.server.entity.*;
import cn.hiauth.server.mapper.AppMapper;
import cn.hiauth.server.mapper.EmployeeMapper;
import cn.hiauth.server.mapper.UserMapper;
import cn.hiauth.server.service.AppResourceService;
import cn.hiauth.server.service.Oauth2RegisteredClientService;
import cn.hiauth.server.service.RoleService;
import cn.hutool.json.JSONUtil;
import cn.webestar.scms.commons.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class MultiAuthUserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private EmployeeMapper employeeMapper;

    @Resource
    private AppMapper appMapper;

    @Resource
    private RoleService roleService;

    @Resource
    private AppResourceService appResourceService;

    @Resource
    private Oauth2RegisteredClientService oauth2RegisteredClientService;

    @Resource
    private RestTemplate restTemplate;

    @Autowired
    private WechatProperties wechatProperties;

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
        Assert.notNull(user, "手机号或者验证码错误");
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

    public AuthUser loadAuthUser(Oauth2RegisteredClient client, User user) {
        Employee employee = null;
        Set<AuthGrantedAuthority> authorities = new HashSet<>();
        if (client != null && client.getAppId() != null) {
            App app = appMapper.selectById(client.getAppId());
            Boolean corpAdminOnly = null;
            if (app != null && app.getExtend() != null && app.getExtend().containsKey("corpAdminOnly")) {
                corpAdminOnly = (Boolean) app.getExtend().get("corpAdminOnly");
            }
            employee = employeeMapper.findOneByAppIdAndUserId(client.getAppId(), user.getId(), corpAdminOnly);
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

    public AuthUser loadUserWeChatCode(String clientId, String code) {
        Map<String, Object> tokenMap = getWechatAccessToken(code);
        String unionId = (String) tokenMap.get("unionid");
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getWxUnionid, unionId);
        User user = userMapper.selectOne(queryWrapper);
        Assert.notNull(user, "账号未注册");
        Oauth2RegisteredClient client = oauth2RegisteredClientService.findByClientId(clientId);
        return loadAuthUser(client, user);
    }

    private Map<String, Object> getWechatAccessToken(String code) {
        String appid = wechatProperties.getAppid();
        String appSecret = wechatProperties.getAppSecret();
        String url = String.format("https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code", appid, appSecret, code);
        String str = restTemplate.getForObject(url, String.class);
        return JSONUtil.toBean(str, Map.class);
    }

    private Map<String, Object> getWechatUserInfo(String accessToken, String openId) {
        String url = String.format("https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s", accessToken, openId);
        String str = restTemplate.getForObject(url, String.class);
        return JSONUtil.toBean(str, Map.class);
    }

}
