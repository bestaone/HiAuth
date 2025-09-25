package cn.hiauth.himall.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@Controller
public class IndexController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${spring.security.oauth2.client.provider.hiauth-server.issuer-uri}")
    private String issuerUri;

    @Value("${spring.security.oauth2.client.provider.hiauth-server.userInfoUri}")
    private String userInfoUri;

    @GetMapping({"/", "/index"})
    public String index(HttpServletRequest request, HttpServletResponse response) {
        return "index";
    }

    @GetMapping("/user/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextHolder.clearContext();
        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "redirect:" + issuerUri + "/unpapi/logoutWithRedirect?redirect_uri=http://127.0.0.1:9000";
    }

    @GetMapping("/profile")
    public String profile(HttpServletRequest request, Model model, @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient oAuth2AuthorizedClient, @AuthenticationPrincipal OidcUser oidcUser) {
        String accessToken = oAuth2AuthorizedClient.getAccessToken().getTokenValue();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", "Bearer " + accessToken);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
        Map<?, ?> res = restTemplate.postForObject(this.userInfoUri, entity, Map.class);
        log.info("res:{}", res);
        model.addAttribute("name", res.get("name"));
        model.addAttribute("username", res.get("username"));
        model.addAttribute("tel", res.get("phoneNum"));
        model.addAttribute("lastLoginTime", LocalDateTime.now());
        request.getSession().setAttribute("isAuth", true);
        return "profile";
    }

}