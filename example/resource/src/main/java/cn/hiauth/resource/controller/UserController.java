package cn.hiauth.resource.controller;

import cn.webestar.scms.commons.R;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@PreAuthorize("hasAuthority('SCOPE_user')")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/info")
    public R<String> info() {
        return R.success("user:zhangsan");
    }

}