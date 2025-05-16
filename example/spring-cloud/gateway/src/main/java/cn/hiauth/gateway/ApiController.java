package cn.hiauth.gateway;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/common")
public class ApiController {

    @GetMapping("/userinfo")
    public OAuth2User userinfo(OAuth2AuthenticationToken authentication) {
        return authentication.getPrincipal();
    }

}