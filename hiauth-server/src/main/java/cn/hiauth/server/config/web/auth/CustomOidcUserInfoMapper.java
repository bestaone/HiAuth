package cn.hiauth.server.config.web.auth;

import cn.hiauth.server.config.web.security.MultiAuthUserService;
import cn.webestar.scms.cache.CacheUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.oidc.authentication.OidcUserInfoAuthenticationContext;

import java.security.Principal;
import java.util.*;
import java.util.function.Function;

/**
 * 自定义用户信息查询
 */
public class CustomOidcUserInfoMapper implements Function<OidcUserInfoAuthenticationContext, OidcUserInfo> {

    private final static Long EXPIRE = 10 * 60 * 60L;

    /**
     * 邮箱对应的属性
     */
    private static final List<String> EMAIL_CLAIMS = Arrays.asList(
            StandardClaimNames.EMAIL,
            StandardClaimNames.EMAIL_VERIFIED
    );

    /**
     * 手机号码对应的属性
     */
    private static final List<String> PHONE_CLAIMS = Arrays.asList(
            StandardClaimNames.PHONE_NUMBER,
            StandardClaimNames.PHONE_NUMBER_VERIFIED
    );

    /**
     * 用户信息对应的属性
     */
    private static final List<String> PROFILE_CLAIMS = Arrays.asList(
            StandardClaimNames.NAME,
            CustomAuthUserAttrs.APP_ID,
            CustomAuthUserAttrs.CID,
            CustomAuthUserAttrs.USER_ID,
            CustomAuthUserAttrs.EMP_ID,
            CustomAuthUserAttrs.NAME,
            CustomAuthUserAttrs.USERNAME,
            CustomAuthUserAttrs.PHONE_NUM,
            CustomAuthUserAttrs.AVATAR_URL,
            CustomAuthUserAttrs.IS_CORP_ADMIN,
            CustomAuthUserAttrs.AUTHORITIES
    );

    private CacheUtil cacheUtil;

    private MultiAuthUserService multiAuthUserService;

    public CustomOidcUserInfoMapper(CacheUtil cacheUtil, MultiAuthUserService multiAuthUserService) {
        this.cacheUtil = cacheUtil;
        this.multiAuthUserService = multiAuthUserService;
    }

    private static Map<String, Object> getClaimsRequestedByScope(Map<String, Object> claims, Set<String> requestedScopes) {
        Set<String> scopeRequestedClaimNames = new HashSet<>(32);
        scopeRequestedClaimNames.add(StandardClaimNames.SUB);
        //是否拥有地址scope
        if (requestedScopes.contains(OidcScopes.ADDRESS)) {
            scopeRequestedClaimNames.add(StandardClaimNames.ADDRESS);
        }
        //是否拥有邮箱scope
        if (requestedScopes.contains(OidcScopes.EMAIL)) {
            scopeRequestedClaimNames.addAll(EMAIL_CLAIMS);
        }
        //是否拥有手机号码scope
        if (requestedScopes.contains(OidcScopes.PHONE)) {
            scopeRequestedClaimNames.addAll(PHONE_CLAIMS);
        }
        //是否拥有用户信息scope
        if (requestedScopes.contains(OidcScopes.PROFILE)) {
            scopeRequestedClaimNames.addAll(PROFILE_CLAIMS);
        }
        Map<String, Object> requestedClaims = new HashMap<>(claims);
        requestedClaims.keySet().removeIf((claimName) -> !scopeRequestedClaimNames.contains(claimName));
        return requestedClaims;
    }

    @Override
    public OidcUserInfo apply(OidcUserInfoAuthenticationContext authenticationContext) {
        OAuth2Authorization authorization = authenticationContext.getAuthorization();
        // 获取登录用户
        Authentication authentication = authorization.getAttribute(Principal.class.getName());
        assert authentication != null;
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        Long userId = authUser.getUserId();
        // 获取最新状态的用户
        OAuth2AccessToken oat = authenticationContext.getAccessToken();
        String clientId = authorization.getRegisteredClientId();
        AuthUser freshAuthUser = multiAuthUserService.loadUserByUserId(clientId, userId);
        cacheUtil.set("userinfo:" + oat.getTokenValue(), freshAuthUser, EXPIRE);
        // 构建用户信息
        Map<String, Object> claims = new HashMap<>(10);
        claims.put(StandardClaimNames.SUB, freshAuthUser.getUsername());
        claims.put(CustomAuthUserAttrs.APP_ID, freshAuthUser.getAppId());
        claims.put(CustomAuthUserAttrs.CID, freshAuthUser.getCid());
        claims.put(CustomAuthUserAttrs.USER_ID, freshAuthUser.getUserId());
        claims.put(CustomAuthUserAttrs.EMP_ID, freshAuthUser.getEmpId());
        claims.put(CustomAuthUserAttrs.NAME, freshAuthUser.getName());
        claims.put(CustomAuthUserAttrs.USERNAME, freshAuthUser.getUsername());
        claims.put(CustomAuthUserAttrs.PHONE_NUM, freshAuthUser.getPhoneNum());
        claims.put(CustomAuthUserAttrs.AVATAR_URL, freshAuthUser.getAvatarUrl());
        claims.put(CustomAuthUserAttrs.IS_CORP_ADMIN, freshAuthUser.getIsCorpAdmin());
        if (freshAuthUser.getAuthorities() != null) {
            Set<Map<String, String>> authorities = new HashSet<>();
            freshAuthUser.getAuthorities().forEach(i -> {
                Map<String, String> map = new HashMap<>(3);
                map.put("code", i.getCode());
                map.put("page", i.getPage());
                map.put("api", i.getApi());
                authorities.add(map);
            });
            claims.put(CustomAuthUserAttrs.AUTHORITIES, authorities);
        }
        Map<String, Object> scopeRequestedClaims = getClaimsRequestedByScope(claims, oat.getScopes());
        return new OidcUserInfo(scopeRequestedClaims);
    }

}
