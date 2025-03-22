package cn.hiauth.client;

import cn.hiauth.client.api.TokenVo;
import cn.hiauth.client.api.UserPwdUpdateDto;
import cn.hiauth.client.api.UserinfoVo;
import cn.hutool.core.codec.Base64;
import cn.webestar.scms.commons.Assert;
import cn.webestar.scms.commons.R;
import cn.webestar.scms.commons.SysCode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/")
public class HiAuthClientController {

    @Autowired
    private HiAuthClientProviderProperties authClientProviderProperties;

    @Autowired
    private HiAuthClientRegistrationProperties authClientRegistrationProperties;

    @Autowired
    private HiAuthClientProperties authClientProperties;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired(required = false)
    private SecurityService securityService;

    @GetMapping("/oauth2/login")
    public String login(HttpServletRequest request) {
        String authUrl = authClientProviderProperties.getAuthorizationUri() +
                "?response_type=code" +
                "&client_id=" + authClientRegistrationProperties.getClientId() +
                "&scope=" + String.join(" ", authClientRegistrationProperties.getScope()) +
                "&redirect_uri=" + authClientRegistrationProperties.getRedirectUri();
        return "redirect:" + authUrl;
    }

    @GetMapping("/oauth2/logout")
    public String logout(HttpServletRequest request) {
        String redirectUri = authClientProperties.getAuthSuccessRedirectUri();
        if (!StringUtils.hasText(redirectUri)) {
            redirectUri = authClientRegistrationProperties.getRedirectUri();
        }
        String logoutUrl = authClientProviderProperties.getIssuerUri() + "/logoutWithRedirect?redirect_uri=" + redirectUri;
        return "redirect:" + logoutUrl;
    }

    @ResponseBody
    @GetMapping(value = "/oauth2/token")
    public R<TokenVo> getTokenJson(HttpServletRequest request, @RequestParam("code") String code) {
        SessionContext context = auth(code);
        long expireIn = ChronoUnit.SECONDS.between(LocalDateTime.now(), context.getExpire());
        TokenVo vo = new TokenVo();
        vo.setAccessToken(context.getAccessToken());
        vo.setRefreshToken(context.getRefreshToken());
        vo.setExpireIn((int) expireIn);
        return R.success(vo);
    }

    @GetMapping(value = "/oauth2/token/redirect")
    public String getTokenHtml(HttpServletRequest request, @RequestParam("code") String code) {
        SessionContext context = auth(code);
        Assert.notNull(authClientProperties.getAuthSuccessRedirectUri(), SysCode.biz(1), "请先配置参数:hiauth.client.authSuccessRedirectUri");
        String customAuthSuccessRedirectUri = request.getHeader("dev-auth-success-redirect-uri");
        String authSuccessRedirectUri = customAuthSuccessRedirectUri != null ? customAuthSuccessRedirectUri : authClientProperties.getAuthSuccessRedirectUri();
        log.debug("REDIRECT-URI:{}?accessToken={}", authSuccessRedirectUri, context.getAccessToken());
        return "redirect:" + authSuccessRedirectUri + "?accessToken=" + context.getAccessToken();
    }

    private SessionContext auth(String code) {
        Assert.notEmpty(code, 300001, "code不能为空。");

        Map<?, ?> tokenMap = getTokenByOauthServer(code);
        assert tokenMap != null;
        Assert.isTrue(tokenMap.containsKey("access_token"), 300002, "无法获取accessToken。");
        String accessToken = (String) tokenMap.get("access_token");
        String refreshToken = (String) tokenMap.get("refresh_token");
        String scope = (String) tokenMap.get("scope");
        Integer expireIn = (Integer) tokenMap.get("expires_in");

        Map<?, ?> userinfoMap = getUserInfoByOauthServer(accessToken);
        Long appId = Long.parseLong(userinfoMap.get("appId").toString());
        Long cid = Long.parseLong(userinfoMap.get("cid").toString());
        Long userId = Long.parseLong(userinfoMap.get("userId").toString());
        Long empId = Long.parseLong(userinfoMap.get("empId").toString());
        String username = (String) userinfoMap.get("username");
        String phoneNum = (String) userinfoMap.get("phoneNum");
        String avatarUrl = (String) userinfoMap.get("avatarUrl");
        String name = (String) userinfoMap.get("name");
        List<Map<String, String>> authorities = (List<Map<String, String>>) userinfoMap.get("authorities");

        HiAuthToken token = new HiAuthToken();
        token.setAccessToken(accessToken);
        token.setRefreshToken(refreshToken);
        token.setScope(scope);
        token.setExpire(LocalDateTime.now().plusSeconds(expireIn));
        Map<String, String> extend = new HashMap<>();

        //设置认证信息
        Authentication auth = new Authentication();
        auth.setAppId(appId);
        auth.setCid(cid);
        auth.setUserId(userId);
        auth.setUsername(username);
        auth.setPhoneNum(phoneNum);
        auth.setAvatarUrl(avatarUrl);
        auth.setEmpId(empId);
        auth.setName(name);
        auth.setAuthorities(authorities);
        //设置用户扩展信息
        if (securityService != null) {
            SecurityUser principal = securityService.loadSecurityUser(auth);
            auth.setPrincipal(principal);
        }

        return SessionContextHolder.auth(auth);
    }

    @ResponseBody
    @GetMapping(value = "/api/common/userinfo")
    public R<UserinfoVo> userinfo(HttpServletRequest request) {
        Authentication auth = SessionContextHolder.getContext().getAuth();
        return R.success(UserinfoVo.toVo(auth));
    }

    @ResponseBody
    @PostMapping(value = "/api/common/updatePwd")
    public Map<?, ?> updatePwd(@RequestBody UserPwdUpdateDto body) {
        SessionContext context = SessionContextHolder.getContext();
        HiAuthToken hiAuthToken = context.getToken();
        return updatePwdByOauthServer(hiAuthToken.getAccessToken(), body.getRawPwd(), body.getNewPwd());
    }

    private Map<?, ?> getTokenByOauthServer(String code) {
        String basicStr = authClientRegistrationProperties.getClientId() + ":" + authClientRegistrationProperties.getClientSecret();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", "Basic " + Base64.encode(basicStr.getBytes()));
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "authorization_code");
        map.add("code", code);
        map.add("redirect_uri", authClientRegistrationProperties.getRedirectUri());
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        return restTemplate.postForObject(authClientProviderProperties.getTokenUri(), request, Map.class);
    }

    private Map<?, ?> getUserInfoByOauthServer(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", "Bearer " + accessToken);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        return restTemplate.postForObject(authClientProviderProperties.getUserInfoUri(), request, Map.class);
    }

    private Map<?, ?> updatePwdByOauthServer(String accessToken, String rawPwd, String newPwd) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + accessToken);
        Map<String, String> map = new HashMap<>(2);
        map.put("rawPwd", rawPwd);
        map.put("pwd", newPwd);
        HttpEntity<Map<String, String>> request = new HttpEntity<>(map, headers);
        return restTemplate.postForObject(authClientProviderProperties.getIssuerUri() + "/oauth2/user/updatePwd", request, Map.class);
    }

}
