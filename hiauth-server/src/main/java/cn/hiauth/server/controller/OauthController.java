package cn.hiauth.server.controller;

import cn.hiauth.server.api.dto.user.UserPwdUpdateDto;
import cn.hiauth.server.config.rest.ResourceApi;
import cn.hiauth.server.entity.User;
import cn.hiauth.server.service.UserService;
import cn.webestar.scms.commons.Assert;
import cn.webestar.scms.commons.R;
import cn.webestar.scms.commons.SysCode;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@ResourceApi
@RestController
@PreAuthorize("isAuthenticated()")
public class OauthController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

//    @GetMapping("/oauth2/user")
//    public R<?> oauth2UserInfo(){
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        Jwt jwt = (Jwt) auth.getPrincipal();
//        Map<String, Object> claims =jwt.getClaims();
//        Long userId = (Long) claims.get("userId");
//        return R.success(userId);
//    }

    @PostMapping("/oauth2/user/updatePwd")
    public R<Boolean> updatePwd(@RequestBody @Validated UserPwdUpdateDto body) {
        // 获取当前用户
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) auth.getPrincipal();
        Map<String, Object> claims = jwt.getClaims();
        Long userId = (Long) claims.get("userId");

        // 查询用户，比对密码
        User user = userService.getById(userId);
        Assert.notNull(user, SysCode.biz(1), "用户不存在");
        Assert.isTrue(passwordEncoder.matches(body.getRawPwd(), user.getPwd()), SysCode.biz(2), "原密码错误");

        // 修改密码
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(User::getPwd, passwordEncoder.encode(body.getPwd()));
        updateWrapper.eq(User::getId, user.getId());

        // 更新密码
        boolean ret = userService.update(updateWrapper);
        return R.success(ret);
    }

}
