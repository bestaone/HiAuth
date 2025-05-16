package cn.hiauth.client;

import cn.hutool.json.JSONUtil;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author zgs
 */
public class SessionContextHolder {

    private static final InheritableThreadLocal<SessionContext> contexts = new InheritableThreadLocal<>();
    private static RedisTemplate<String, String> redisTemplate;

    public static void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        SessionContextHolder.redisTemplate = redisTemplate;
    }

    public static SessionContext getContext() {
        return contexts.get();
    }

    public static void setContext(SessionContext context) {
        contexts.set(context);
        //refresh();
    }

    public static Authentication getAuthentication() {
        SessionContext context = getContext();
        return context == null ? null : context.getAuth();
    }

    public static SecurityUser getPrincipal() {
        Authentication auth = getAuthentication();
        return auth == null ? null : auth.getPrincipal();
    }

    public static void refresh() {
        SessionContext context = contexts.get();
        String tokenKey = String.format(Constant.ACCESS_TOKEN_CACHE_KEY, context.getCachePrefix(), context.getAuth().getUserId(), context.getAccessToken());
        String refreshTokenKey = String.format(Constant.REFRESH_TOKEN_CACHE_KEY, context.getCachePrefix(), context.getAuth().getUserId(), context.getRefreshToken());
        String json = JSONUtil.toJsonStr(context);
        redisTemplate.opsForValue().set(tokenKey, json, Constant.ACCESS_TOKEN_EXPIRE, TimeUnit.SECONDS);
        redisTemplate.expire(refreshTokenKey, Constant.REFRESH_TOKEN_EXPIRE, TimeUnit.SECONDS);
    }

    public static SessionContext auth(SessionContext context) {

        String userId = context.getAuth().getUserId().toString();
        String accessToken = JwtUtils.generateToken(userId);
        String refreshToken = UUID.randomUUID().toString().replace("-", "");
        context.setAccessToken(accessToken);
        context.setRefreshToken(refreshToken);
        context.setExpire(LocalDateTime.now().plusMinutes(JwtUtils.EXPIRE));

        SessionContextHolder.setContext(context);
        String json = JSONUtil.toJsonStr(context);
        redisTemplate.opsForValue().set(String.format(Constant.ACCESS_TOKEN_CACHE_KEY, context.getCachePrefix(), userId, accessToken), json, Constant.ACCESS_TOKEN_EXPIRE, TimeUnit.SECONDS);
        redisTemplate.opsForValue().set(String.format(Constant.REFRESH_TOKEN_CACHE_KEY, context.getCachePrefix(), userId, refreshToken), accessToken, Constant.REFRESH_TOKEN_EXPIRE, TimeUnit.SECONDS);

        return context;
    }

    public static SessionContext auth(String clientName, String cachePrefix, Authentication authentication) {
        SessionContext context = new SessionContext(clientName, cachePrefix);
        context.setAuth(authentication);
        return auth(context);
    }

    public static void logout() {
        SessionContext context = SessionContextHolder.getContext();
        String username = context.getAuth().getUsername();
        String accessToken = context.getAccessToken();
        String refreshToken = context.getRefreshToken();
        if (redisTemplate != null) {
            redisTemplate.expire(String.format(Constant.ACCESS_TOKEN_CACHE_KEY, context.getCachePrefix(), username, accessToken), 0, TimeUnit.SECONDS);
            redisTemplate.expire(String.format(Constant.REFRESH_TOKEN_CACHE_KEY, context.getCachePrefix(), username, refreshToken), 0, TimeUnit.SECONDS);
        }
    }

}