package com.bestaone.aiwan.order.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @ResponseBody
    @GetMapping("/test/a")
    public String test() {
        return "test!";
    }

}
