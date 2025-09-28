package cn.hiauth.gateway;

import cn.hiauth.client.Authentication;
import cn.hiauth.client.SessionContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/api/info")
    public Authentication index(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        Authentication auth = SessionContextHolder.getContext().getAuth();
        return auth;
    }

}