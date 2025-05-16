package cn.hiauth.client.gateway;

import cn.hiauth.client.Constant;
import cn.hiauth.client.JwtUtils;
import cn.hiauth.client.SessionContext;
import cn.hiauth.client.SessionContextHolder;
import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWT;
import cn.webestar.scms.commons.Assert;
import cn.webestar.scms.commons.CommonException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

/**
 * @author zgs
 */
@Slf4j
public class AuthGatewayFilterFactory extends AbstractGatewayFilterFactory<AuthGatewayFilterFactory.Config> {

    private final AntPathMatcher matcher = new AntPathMatcher();
    private final HiAuthClientGatewayProperties hiAuthClientGatewayProperties;
    private final RedisTemplate<String, String> redisTemplate;

    public AuthGatewayFilterFactory(HiAuthClientGatewayProperties hiAuthClientGatewayProperties, RedisTemplate<String, String> redisTemplate) {
        super(Config.class);
        this.hiAuthClientGatewayProperties = hiAuthClientGatewayProperties;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            HiAuthClientGatewayProperties.Client client = hiAuthClientGatewayProperties.getClients().get(config.getClientName());
            try {
                // 检查是否已认证
                checkAuth(exchange, client.getCachePrefix());
                //TODO 检查是否已授权
            } catch (Exception e) {
                return handleException(exchange, e);
            }
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            })).then();
        };
    }

    private void checkAuth(ServerWebExchange exchange, String cachePrefix) {
        ServerHttpRequest request = exchange.getRequest();
        final String url = request.getPath().pathWithinApplication().value();
        final String method = exchange.getRequest().getMethod().name();
        if (Constant.IGNORE_METHOD.equalsIgnoreCase(method) || !matcherAuthUrl(url)) {
            return;
        }
        SessionContext context = getSessionContext(request, cachePrefix);
        Assert.notNull(context, 10401, "request fail");
        Assert.notNull(cachePrefix, 10401, "cachePrefix is null");
        Assert.isTrue(cachePrefix.equals(context.getCachePrefix()), 10401, "invalid token");
        SessionContextHolder.setContext(context);
    }

    public boolean matcherAuthUrl(String uri) {
        for (String authUrl : hiAuthClientGatewayProperties.getAuthUris()) {
            if (matcher.match(authUrl, uri)) {
                return true;
            }
        }
        return false;
    }

    private SessionContext getSessionContext(ServerHttpRequest request, String cachePrefix) {

        String authHeader = request.getHeaders().getFirst(Constant.TOKEN_HEADER);
        Assert.notNull(authHeader, 10401, "miss token");

        authHeader = URLDecoder.decode(authHeader, StandardCharsets.UTF_8);
        Assert.isTrue(authHeader.startsWith(Constant.TOKEN_PREFIX), 10401, "miss bearer");

        String accessToken = authHeader.substring(Constant.TOKEN_PREFIX.length()).trim();
        JWT jwt = JwtUtils.parseToken(accessToken);
        Assert.notNull(jwt, 10401, "invalid token");

        String username = (String) jwt.getPayload(JwtUtils.SUB_KEY);
        Assert.notNull(username, 10401, "invalid token");

        String accessTokenKey = String.format(Constant.ACCESS_TOKEN_CACHE_KEY, cachePrefix, username, accessToken);
        String json = redisTemplate.opsForValue().get(accessTokenKey);
        Assert.notNull(json, 10401, "invalid token");
        SessionContext context = JSONUtil.toBean(json, SessionContext.class);
        Assert.notNull(context, 10401, "invalid token");

        return context;
    }

    private Mono<Void> handleException(ServerWebExchange exchange, Throwable ex) {
        // 设置响应状态码
        exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

        Integer code = 50000;
        String msg;

        if (ex instanceof CommonException ce) {
            code = ce.getCode();
            msg = ex.getMessage();
        } else {
            msg = "系统异常";
        }

        byte[] bytes = String.format(Constant.RESULT_JSON, code, msg).getBytes();
        DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
        return exchange.getResponse().writeWith(Mono.just(buffer));
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("clientName", "enabled");
    }

    @Data
    public static class Config {
        private String clientName;
        private boolean enabled;
    }

}