package cn.hiauth.auth.controller;

import cn.hiauth.auth.domain.AuthorizationConsent;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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
    private JdbcTemplate jdbcTemplate;

    @GetMapping(value = {"/", "/index"})
    public String indexHtml() {
        return "index";
    }

    @ResponseBody
    @GetMapping(value = {"/me"})
    public String me() {
        return "zhangsan";
    }

//    @ResponseBody
//    @GetMapping(value = {"/", "/index"}, produces = "application/json")
//    public Authentication indexJson(Authentication auth) {
//        return auth;
//    }

    @GetMapping(value="/logout")
    public String logout (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "login";
    }

    @GetMapping({"/user/me"})
    public String me(Model model, Authentication auth) {
        if(auth.getPrincipal()!=null ){
            User user = (User) auth.getPrincipal();
            model.addAttribute("user", user);
            List<AuthorizationConsent> authorizationConsentList = myAuthorizationConsentList(user.getUsername());
            model.addAttribute("authorizationConsentList", authorizationConsentList);
        }
        return "user/me";
    }

    private List<AuthorizationConsent> myAuthorizationConsentList(String username){
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
