package cn.hiauth.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "gateway";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

}