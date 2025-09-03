package cn.hiauth.server.controller;

import cn.hiauth.server.api.vo.IndexCorpAppVo;
import cn.hiauth.server.config.props.AppProperties;
import cn.hiauth.server.config.props.WechatProperties;
import cn.hiauth.server.config.web.auth.AuthUser;
import cn.hiauth.server.entity.*;
import cn.hiauth.server.mapper.CorpAppMapper;
import cn.hiauth.server.service.AppService;
import cn.hiauth.server.service.CorpService;
import cn.hiauth.server.service.EmployeeService;
import cn.hiauth.server.service.Oauth2RegisteredClientService;
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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Autowired
    private CorpService corpService;

    @Autowired
    private AppService appService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private WechatProperties wechatProperties;

    @Autowired
    private Oauth2RegisteredClientService oauth2RegisteredClientService;

    @Autowired
    private CorpAppMapper corpAppMapper;

    @GetMapping(value = {"/", "/index"})
    public String index(HttpServletRequest request, Model model, Authentication auth) {
        AuthUser authUser = (AuthUser) auth.getPrincipal();
        //如果主动添加了client_id,则跳转到对应的系统
        String clientId = (String) request.getSession().getAttribute("clientId");
        if (StringUtils.hasText(clientId)) {
            Oauth2RegisteredClient client = oauth2RegisteredClientService.findByClientId(clientId);
            if (client != null && client.getAppId() != null) {
                List<CorpAppInfo> cpis = corpAppMapper.limitCorpAppInfoByUserId(authUser.getUserId(), client.getAppId());
                if (cpis != null && !cpis.isEmpty() && StringUtils.hasText(cpis.get(0).getAppHome())) {
                    return "redirect:" + cpis.get(0).getAppHome();
                }
            }
        }
        //如果主动添加了client_id,则跳转到对应的系统
        List<IndexCorpAppVo> indexCorpApps = corpService.findIndexCorpAppByUserId(authUser.getUserId(), null);
        model.addAttribute("corpApps", indexCorpApps);
        return "index";
    }

    @GetMapping(value = {"/openApp"})
    public String openApp(@RequestParam("cid") Long cid, @RequestParam("appId") Long appId, Authentication auth) {
        AuthUser authUser = (AuthUser) auth.getPrincipal();
        App app = appService.getById(appId);
        employeeService.swichCorp(authUser.getUserId(), cid);
        return "redirect:" + app.getHome();
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
        // 登录方式
        model.addAttribute("loginTypes", appProperties.getLoginTypes());
        // 登录页面配置
        model.addAttribute(Constant.REQUEST_KEY_FORM_TOKEN, idGenerator.nextId());
        model.addAttribute("title", appProperties.getLoginPageTitle());
        model.addAttribute("username", appProperties.getLoginPageUsername());
        model.addAttribute("password", appProperties.getLoginPagePassword());
        model.addAttribute("usernamePlaceholder", appProperties.getLoginPageUsernamePlaceholder());
        model.addAttribute("passwordPlaceholder", appProperties.getLoginPagePasswordPlaceholder());
        // 微信登录配置
        model.addAttribute("wechatAppid", wechatProperties.getAppid());
        model.addAttribute("wechatRedirectUri", wechatProperties.getRedirectUri());
        model.addAttribute("wechatStyle", wechatProperties.getStyle());
        model.addAttribute("wechatHref", wechatProperties.getHref());
        model.addAttribute("wechaState", idGenerator.nextId());
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
