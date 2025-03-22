package cn.hiauth.himall.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @ResponseBody
    @GetMapping("/api/client")
    public OAuth2AuthorizedClient client(HttpServletRequest request, @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient oAuth2AuthorizedClient) {
        // 通过OAuth2AuthorizedClient对象获取到客户端和令牌相关的信息，然后直接返回给前端页面
        request.getSession().setAttribute("isAuth", true);
        return oAuth2AuthorizedClient;
    }

}
