package com.bestaone.aiwan.auth.web.controller;

import com.bestaone.aiwan.auth.service.AccountService;
import com.bestaone.aiwan.auth.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@PreAuthorize("isAuthenticated()")
public class WebController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	AccountService accountService;

	@Autowired
	UserService userService;

    @GetMapping(value = {"/index"}, produces = "text/html")
    public String indexHtml() {
        return "/index";
    }

    @ResponseBody
	@GetMapping(value = {"/index"}, produces = "application/json")
	public Authentication indexJson(Authentication auth) {
		return auth;
	}

	@GetMapping({"/user/detail"})
	public String detail(Model model, Authentication auth) {
		model.addAttribute("auth", auth);
		return "/user/detail";
	}

	@PreAuthorize("hasAuthority('/user/me')")
	@GetMapping({"/user/me"})
	public String me(Model model, Authentication auth) {
		model.addAttribute("auth", auth);
		return "/user/me";
	}

	@PreAuthorize("hasAuthority('/user/list')")
	@GetMapping({"/user/list"})
	public String list(Model model, Authentication auth) {
		model.addAttribute("auth", auth);
		return "/user/list";
	}

}
