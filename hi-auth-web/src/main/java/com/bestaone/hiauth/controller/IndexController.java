package com.bestaone.hiauth.controller;

import com.bestaone.hiauth.domain.Resource;
import com.bestaone.hiauth.domain.Role;
import com.bestaone.hiauth.domain.User;
import com.bestaone.hiauth.service.ResourceService;
import com.bestaone.hiauth.service.RoleService;
import com.bestaone.hiauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@PreAuthorize("isAuthenticated()")
public class IndexController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    ResourceService resourceService;

    @GetMapping(value = {"/", "/index"})
    public String indexHtml() {
        return "/index";
    }

    @ResponseBody
	@GetMapping(value = {"/", "/index"}, produces = "application/json")
	public Authentication indexJson(Authentication auth) {
		return auth;
	}

    @PreAuthorize("hasAuthority('/user/me')")
    @GetMapping({"/user/me"})
    public String me(Model model, Authentication auth) {

        if(auth.getPrincipal()!=null ){
            User user = userService.findByUsername(((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername());
            List<Role> roles = null;
            List<Resource> resources = null;
            if(user!=null){
                roles = roleService.findByUserId(user.getId());
                resources = resourceService.findByUserId(user.getId());
            }
            model.addAttribute("user", user);
            model.addAttribute("roles", roles);
            model.addAttribute("resources", resources);
        }

        return "/user/me";
    }

}
