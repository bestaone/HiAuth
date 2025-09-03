//package cn.hiauth.server.config.web.auth;
//
//import cn.hiauth.server.entity.CorpAppInfo;
//import cn.hiauth.server.entity.Oauth2RegisteredClient;
//import cn.hiauth.server.mapper.CorpAppMapper;
//import cn.hiauth.server.service.Oauth2RegisteredClientService;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
//import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationCodeRequestAuthenticationToken;
//import org.springframework.security.web.DefaultRedirectStrategy;
//import org.springframework.security.web.RedirectStrategy;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.util.StringUtils;
//import org.springframework.web.util.UriComponentsBuilder;
//import org.springframework.web.util.UriUtils;
//
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.util.List;
//import java.util.Objects;
//
///**
// * 自定义授权响应处理，后期看看能不能删除这个自定义功能，把对应的功能写到CustomAuthenticationSuccessHandler中
// */
//@Slf4j
//public class CustomAuthorizationResponseHandler implements AuthenticationSuccessHandler {
//
//    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
//
//    private Oauth2RegisteredClientService oauth2RegisteredClientService;
//
//    private CorpAppMapper corpAppMapper;
//
//    public CustomAuthorizationResponseHandler(Oauth2RegisteredClientService oauth2RegisteredClientService, CorpAppMapper corpAppMapper) {
//        this.oauth2RegisteredClientService = oauth2RegisteredClientService;
//        this.corpAppMapper = corpAppMapper;
//    }
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
//        OAuth2AuthorizationCodeRequestAuthenticationToken token = (OAuth2AuthorizationCodeRequestAuthenticationToken) authentication;
//        String redirectUri = token.getRedirectUri();
//
//        // 自定义逻辑
//        log.info("自定义逻辑开始: {}、{}", token.getClientId(), redirectUri);
//        String loginClientId = (String) request.getSession().getAttribute("clientId");
//        String loginRedirectUri = (String) request.getSession().getAttribute("redirectUri");
//        if (StringUtils.hasText(loginClientId)
//                && token.getClientId() != null
//                && !loginClientId.equals(token.getClientId())
//                && StringUtils.hasText(loginRedirectUri)) {
//            redirectUri = modifyRedirectUrl(token, loginClientId, loginRedirectUri, redirectUri);
//        }
//        log.info("自定义逻辑结束");
//        // 自定义逻辑
//
//        UriComponentsBuilder uriBuilder = UriComponentsBuilder
//                .fromUriString(Objects.requireNonNull(redirectUri))
//                .queryParam(OAuth2ParameterNames.CODE, Objects.requireNonNull(token.getAuthorizationCode()).getTokenValue());
//        if (StringUtils.hasText(token.getState())) {
//            uriBuilder.queryParam(OAuth2ParameterNames.STATE, UriUtils.encode(token.getState(), StandardCharsets.UTF_8));
//        }
//        redirectUri = uriBuilder.build(true).toUriString();
//
//        this.redirectStrategy.sendRedirect(request, response, redirectUri);
//    }
//
//    private String modifyRedirectUrl(OAuth2AuthorizationCodeRequestAuthenticationToken token, String loginClientId, String loginRedirectUri, String redirectUri) {
//        log.info("1: {}、{}、{}", loginClientId, loginRedirectUri, redirectUri);
//        // 无登录信息或者登录信息类型错误，直接返回
//        if (token.getPrincipal() == null || !(token.getPrincipal() instanceof UsernamePasswordAuthenticationToken)) {
//            return redirectUri;
//        }
//        UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) token.getPrincipal();
//
//        log.info("2: {}、{}、{}", token.getPrincipal(), loginRedirectUri, redirectUri);
//        // 无登录用户信息或者登录用户信息类型错误，直接返回
//        if (authToken.getPrincipal() == null || !(authToken.getPrincipal() instanceof AuthUser)) {
//            return redirectUri;
//        }
//        AuthUser authUser = (AuthUser) authToken.getPrincipal();
//
//        log.info("3: {}、{}、{}", authToken.getPrincipal(), loginRedirectUri, redirectUri);
//        // 获取登录应用信息
//        Oauth2RegisteredClient client = oauth2RegisteredClientService.findByClientId(loginClientId);
//        if (client == null || client.getAppId() == null) {
//            return redirectUri;
//        }
//
//        log.info("4: {}、{}、{}", client.getAppId(), loginRedirectUri, redirectUri);
//        // 检查是否有权限
//        List<CorpAppInfo> cpis = corpAppMapper.limitCorpAppInfoByUserId(authUser.getUserId(), client.getAppId());
//        if (cpis == null || cpis.isEmpty()) {
//            return redirectUri;
//        }
//
//        log.info("5: {}、{}、{}", cpis, loginRedirectUri, redirectUri);
//        return loginRedirectUri;
//    }
//
//}
