package cn.hiauth.wechatlogin.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Controller
public class IndexController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping({"/", "/index"})
    public String index(HttpServletRequest request, HttpServletResponse response) {
        return "index";
    }

}