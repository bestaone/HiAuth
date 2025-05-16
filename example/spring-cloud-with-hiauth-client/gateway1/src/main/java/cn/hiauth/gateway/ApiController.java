package cn.hiauth.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/common")
public class ApiController {

    @GetMapping("/userinfo")
    public String userinfo() {
        return "TEST";
    }

}