package cn.hiauth.client.gateway;

import cn.hiauth.client.*;
import cn.hutool.core.codec.Base64;
import cn.webestar.scms.commons.Assert;
import cn.webestar.scms.commons.R;
import cn.webestar.scms.commons.SysCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/")
public class HiAuthClientGatewayController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired(required = false)
    private SecurityService securityService;

    @Autowired
    private HiAuthClientGatewayProperties hiauthClientProperties;

    @GetMapping("/unpapi/{clientName}/oauth2/login")
    public Mono<Void> login(@PathVariable("clientName") String clientName, ServerWebExchange exchange) {
        Client client = hiauthClientProperties.getClients().get(clientName);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(hiauthClientProperties.getAuthorizationUri())
                .queryParam("response_type", "code")
                .queryParam("client_id", client.getClientId())
                .queryParam("scope", String.join(" ", client.getScope()))
                .queryParam("redirect_uri", client.getRedirectUri());
        exchange.getResponse().setStatusCode(HttpStatus.TEMPORARY_REDIRECT);
        exchange.getResponse().getHeaders().setLocation(uriBuilder.build().toUri());
        return exchange.getResponse().setComplete();
    }

    @GetMapping(value = "/unpapi/{clientName}/oauth2/token/redirect")
    public Mono<Void> getTokenHtml(@PathVariable("clientName") String clientName, @RequestParam("code") String code, ServerWebExchange exchange) {
        Client client = hiauthClientProperties.getClients().get(clientName);
        Assert.notNull(client.getAuthSuccessRedirectUri(), SysCode.biz(1), "请先配置参数:hiauth.client.authSuccessRedirectUri");
        String customAuthSuccessRedirectUri = exchange.getRequest().getHeaders().getFirst("dev-auth-success-redirect-uri");
        String authSuccessRedirectUri = customAuthSuccessRedirectUri != null ? customAuthSuccessRedirectUri : client.getAuthSuccessRedirectUri();
        try {
            SessionContext context = auth(clientName, client, code);
            log.debug("REDIRECT-URI:{}?accessToken={}", authSuccessRedirectUri, context.getAccessToken());
            UriComponentsBuilder uriBuilder = UriComponentsBuilder
                    .fromUriString(authSuccessRedirectUri)
                    .queryParam("accessToken", context.getAccessToken());
            exchange.getResponse().setStatusCode(HttpStatus.TEMPORARY_REDIRECT);
            exchange.getResponse().getHeaders().setLocation(uriBuilder.build().toUri());
            return exchange.getResponse().setComplete();
        } catch (HttpClientErrorException e) {
            log.debug("权限不足，退出重新登陆。");
            return logout(clientName, exchange);
        }
    }

    @GetMapping("/unpapi/{clientName}/oauth2/logout")
    public Mono<Void> logout(@PathVariable("clientName") String clientName, ServerWebExchange exchange) {
        Client client = hiauthClientProperties.getClients().get(clientName);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(hiauthClientProperties.getIssuerUri() + "/logoutWithRedirect")
                .queryParam("redirect_uri", client.getAuthSuccessRedirectUri());
        exchange.getResponse().setStatusCode(HttpStatus.TEMPORARY_REDIRECT);
        exchange.getResponse().getHeaders().setLocation(uriBuilder.build().toUri());
        return exchange.getResponse().setComplete();
    }

    @ResponseBody
    @GetMapping(value = "/api/common/userinfo")
    public R<UserinfoVo> userinfo() {
        Authentication auth = SessionContextHolder.getContext().getAuth();
        return R.success(UserinfoVo.toVo(auth));
    }

    @ResponseBody
    @PostMapping(value = "/api/common/updatePwd")
    public Map<?, ?> updatePwd(@RequestBody UserPwdUpdateDto body) {
        SessionContext context = SessionContextHolder.getContext();
        HiAuthToken token = context.getToken();
        return updatePwdByOauthServer(token.getAccessToken(), body.getRawPwd(), body.getNewPwd());
    }

    private SessionContext auth(String clientName, Client client, String code) throws HttpClientErrorException {
        Assert.notEmpty(code, 300001, "code不能为空。");
        Map<?, ?> tokenMap = getTokenByOauthServer(client, code);
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

        Boolean isCorpAdmin = null;
        if(userinfoMap.containsKey("isCorpAdmin")){
            isCorpAdmin = (Boolean) userinfoMap.get("isCorpAdmin");
        }

        HiAuthToken token = new HiAuthToken();
        token.setAccessToken(accessToken);
        token.setRefreshToken(refreshToken);
        token.setScope(scope);
        token.setExpire(LocalDateTime.now().plusSeconds(expireIn));

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
        auth.setIsCorpAdmin(isCorpAdmin);
        //设置用户扩展信息
        if (securityService != null) {
            SecurityUser principal = securityService.loadSecurityUser(auth);
            auth.setPrincipal(principal);
        }

        SessionContext context = new SessionContext(clientName, client.getCachePrefix(), client.getCacheExpire());
        context.setToken(token);
        context.setAuth(auth);

        return SessionContextHolder.auth(context);
    }

    private Map<?, ?> getTokenByOauthServer(Client client, String code) {
        String basicStr = client.getClientId() + ":" + client.getClientSecret();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", "Basic " + Base64.encode(basicStr.getBytes()));
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "authorization_code");
        map.add("code", code);
        map.add("redirect_uri", client.getRedirectUri());
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        return restTemplate.postForObject(hiauthClientProperties.getTokenUri(), request, Map.class);
    }

    private Map<?, ?> getUserInfoByOauthServer(String accessToken) throws HttpClientErrorException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", "Bearer " + accessToken);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        return restTemplate.postForObject(hiauthClientProperties.getUserInfoUri(), request, Map.class);
    }

    private Map<?, ?> updatePwdByOauthServer(String accessToken, String rawPwd, String newPwd) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + accessToken);
        Map<String, String> map = new HashMap<>(2);
        map.put("rawPwd", rawPwd);
        map.put("pwd", newPwd);
        HttpEntity<Map<String, String>> request = new HttpEntity<>(map, headers);
        return restTemplate.postForObject(hiauthClientProperties.getIssuerUri() + "/oauth2/user/updatePwd", request, Map.class);
    }

}
