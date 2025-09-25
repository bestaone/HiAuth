package cn.hiauth.resource.controller;

import cn.webestar.scms.commons.R;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@PreAuthorize("hasAuthority('SCOPE_profile')")
@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    @GetMapping("/info")
    public R<String> info() {
        return R.success("profile:superman");
    }

}