package cn.hiauth.hiauthclient.controller;

import cn.hiauth.client.Authentication;
import cn.hiauth.client.Constant;
import cn.hiauth.client.SessionContextHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Slf4j
@Controller
public class IndexController {

    @GetMapping({"/", "/index"})
    public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
        // 这个为了简单就直接判断accessToken了，实际情况应该判断 SessionContextHolder.getContext().getAuth()
        String accessToken = request.getParameter(Constant.PARAMETER_TOKEN_KEY);
        if(StringUtils.hasText(accessToken)){
            request.getSession().setAttribute("isAuth", true);
            model.addAttribute(Constant.PARAMETER_TOKEN_KEY, accessToken);
        } else {
            request.getSession().setAttribute("isAuth", false);
        }
        return "index";
    }

    @GetMapping("/api/profile")
    public String profile(HttpServletRequest request, Model model) {
        Authentication auth = SessionContextHolder.getContext().getAuth();
        model.addAttribute(Constant.PARAMETER_TOKEN_KEY, request.getParameter(Constant.PARAMETER_TOKEN_KEY));
        model.addAttribute("name", auth.getName());
        model.addAttribute("username", auth.getUsername());
        model.addAttribute("tel", auth.getPhoneNum());
        model.addAttribute("lastLoginTime", LocalDateTime.now());
        request.getSession().setAttribute("isAuth", true);
        return "profile";
    }

}