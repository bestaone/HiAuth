package cn.hiauth.mgr.controller;

import cn.hiauth.mgr.api.vo.user.CurrentUserVO;
import cn.hiauth.mgr.common.BaseController;
import cn.hiauth.mgr.config.security.UserContent;
import cn.hiauth.mgr.domain.User;
import cn.hiauth.mgr.service.UserService;
import com.baomidou.mybatisplus.extension.service.IService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

//@Api(tags = {"用户"})
@RequestMapping("/api/user")
@RestController
public class UserController extends BaseController<User> {

    @Autowired
    private UserService service;

    @Override
    public IService getService() {
        return service;
    }

    @GetMapping("/getCurrentUserInfo")
    public CurrentUserVO getCurrentUserVO() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserContent userContent = (UserContent) authentication.getPrincipal();
        User user = service.getById(userContent.getUserId());
        CurrentUserVO currentUserVO = new CurrentUserVO();
        currentUserVO.setUserId(userContent.getUserId());
        currentUserVO.setAvatar(user.getAvatarUrl());
        currentUserVO.setStatus(1);
        currentUserVO.setDesc("");
        currentUserVO.setNickname(user.getUsername());
        currentUserVO.setUnreadCount(11);
        return currentUserVO;
    }

//    @ApiOperation(value = "查询当前用户拥有的资源")
    @PostMapping("/findCurrentResources")
    public Set<String> findCurrentResources() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserContent userContent = (UserContent) authentication.getPrincipal();
//        List<Resource> resources = resourceService.findByEmp(userContent.getEmpId());
        Set<String> rSet = new HashSet<>();
//        resources.forEach(e -> rSet.add(e.getCode()));
        return rSet;
    }

//    @ApiOperation(value = "查询当前用户拥有的权限")
    @PostMapping("/findCurrentPermissions")
    public Set<String> findCurrentPermissions() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserContent userContent = (UserContent) authentication.getPrincipal();
//        List<Permission> permissions = permissionService.findByEmp(userContent.getEmpId());
        Set<String> rSet = new HashSet<>();
//        permissions.forEach(e -> rSet.add(e.getCode()));
        return rSet;
    }

}
