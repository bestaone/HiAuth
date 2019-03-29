package com.bestaone.hiauth.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@PreAuthorize("isAuthenticated()")
public class TestController {

	@GetMapping({"/user/detail"})
	public String detail(Model model, Authentication auth) {
		model.addAttribute("auth", auth);
		return "/user/detail";
	}

	@PreAuthorize("hasAuthority('/user/list')")
	@GetMapping({"/user/list"})
	public String list(Model model, Authentication auth) {
		model.addAttribute("auth", auth);
		return "/user/list";
	}

}
