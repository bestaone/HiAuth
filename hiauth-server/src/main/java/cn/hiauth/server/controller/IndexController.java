package cn.hiauth.server.controller;

import cn.hiauth.server.config.AppProperties;
import cn.hiauth.server.entity.AuthorizationConsent;
import cn.hiauth.server.entity.User;
import cn.hiauth.server.utils.Constant;
import cn.hutool.core.lang.Snowflake;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@PreAuthorize("isAuthenticated()")
public class IndexController {

    @Autowired
    private AppProperties appProperties;

    @Autowired
    private Snowflake idGenerator;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping(value = {"/", "/index"})
    public String index(Model model, Authentication auth) {
        return "index";
    }

    @GetMapping(value = {"/profile"})
    public String profile(Authentication auth) {
        return "profile";
    }

    @GetMapping(value = {"/setting"})
    public String setting(Authentication auth) {
        return "setting";
    }

    @ResponseBody
    @GetMapping(value = {"/me"})
    public String me() {
        return "zhangsan";
    }

    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        model.addAttribute(Constant.REQUEST_KEY_FORM_TOKEN, idGenerator.nextId());
        model.addAttribute("title", appProperties.getLoginPageTitle());
        model.addAttribute("username", appProperties.getLoginPageUsername());
        model.addAttribute("password", appProperties.getLoginPagePassword());
        model.addAttribute("usernamePlaceholder", appProperties.getLoginPageUsernamePlaceholder());
        model.addAttribute("passwordPlaceholder", appProperties.getLoginPagePasswordPlaceholder());
        return appProperties.getLoginPagePath();
    }

    @GetMapping({"/user/me"})
    public String me(Model model, Authentication auth) {
        if (auth.getPrincipal() != null) {
            User user = (User) auth.getPrincipal();
            model.addAttribute("user", user);
            List<AuthorizationConsent> authorizationConsentList = myAuthorizationConsentList(user.getUsername());
            model.addAttribute("authorizationConsentList", authorizationConsentList);
        }
        return "user/me";
    }

    private List<AuthorizationConsent> myAuthorizationConsentList(String username) {
        String sql = """
                SELECT
                	T.registered_client_id  registered_client_id,
                	T.principal_name        principal_name,
                	T.authorities           authorities,
                	RC.client_name 	        client_name
                FROM oauth2_authorization_consent T
                LEFT JOIN oauth2_registered_client RC ON RC.id=T.registered_client_id
                WHERE T.principal_name='%s'
                """;
        List<AuthorizationConsent> authorizationConsentList = jdbcTemplate.query(String.format(sql, username), new BeanPropertyRowMapper(AuthorizationConsent.class));
        return authorizationConsentList;
    }

}
