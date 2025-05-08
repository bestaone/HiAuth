package cn.hiauth.hiauthclient.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Slf4j
@Controller
public class IndexController {

    @GetMapping({"/", "/index"})
    public String index(HttpServletRequest request, HttpServletResponse response) {
        return "index";
    }

    @GetMapping("/profile")
    public String profile(HttpServletRequest request, Model model) {
        model.addAttribute("lastLoginTime", LocalDateTime.now());
        request.getSession().setAttribute("isAuth", true);
        return "profile";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        // TODO: 清空cookes
        request.getSession().setAttribute("isAuth", false);
        return "index";
    }

}