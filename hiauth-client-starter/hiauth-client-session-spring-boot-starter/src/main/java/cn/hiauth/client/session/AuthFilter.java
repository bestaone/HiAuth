package cn.hiauth.client.session;

import cn.hiauth.client.Constant;
import cn.hiauth.client.JwtUtils;
import cn.hiauth.client.SessionContext;
import cn.hiauth.client.SessionContextHolder;
import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWT;
import cn.webestar.scms.commons.Assert;
import cn.webestar.scms.commons.CommonException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.AntPathMatcher;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * @author zgs
 */
@Slf4j
public class AuthFilter implements Filter {

    private final AntPathMatcher matcher = new AntPathMatcher();

    private final RedisTemplate<String, String> redisTemplate;

    private final HiAuthClientSessionProperties properties;

    public AuthFilter(HiAuthClientSessionProperties properties, RedisTemplate<String, String> redisTemplate) {
        this.properties = properties;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        try {
            doIt(request, response, chain);
        } catch (Exception e) {
            printError(request, response, e);
        }
    }

    private void doIt(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws Exception {

        if (Constant.IGNORE_METHOD.equalsIgnoreCase(request.getMethod())) {
            chain.doFilter(request, response);
        } else if (matcherUrl(request.getRequestURI())) {
            SessionContext context = getSessionContext(request);
            Assert.notNull(context, 10401, "request fail");
            SessionContextHolder.setContext(context);
            chain.doFilter(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    private SessionContext getSessionContext(HttpServletRequest request) throws Exception {

        String authHeader = request.getHeader(Constant.TOKEN_HEADER);
        Assert.notNull(authHeader, 10401, "miss token");

        authHeader = URLDecoder.decode(authHeader, StandardCharsets.UTF_8);
        Assert.isTrue(authHeader.startsWith(Constant.TOKEN_PREFIX), 10401, "miss bearer");

        String accessToken = authHeader.substring(Constant.TOKEN_PREFIX.length()).trim();
        JWT jwt = JwtUtils.parseToken(accessToken);
        Assert.notNull(jwt, 10401, "invalid token");

        String username = (String) jwt.getPayload(JwtUtils.SUB_KEY);
        Assert.notNull(username, 10401, "invalid token");

        String accessTokenKey = String.format(Constant.ACCESS_TOKEN_CACHE_KEY, properties.getCachePrefix(), username, accessToken);
        String json = redisTemplate.opsForValue().get(accessTokenKey);
        Assert.notNull(json, 10401, "invalid token");
        SessionContext context = JSONUtil.toBean(json, SessionContext.class);
        Assert.notNull(context, 10401, "invalid token");

        return context;
    }

    public boolean matcherUrl(String uri) {
        for (String authUrl : properties.getAuthUris()) {
            if (matcher.match(authUrl, uri)) {
                return true;
            }
        }
        return false;
    }

    public boolean ignoreUrl(String uri) {
        for (String ignoreUrl : properties.getIgnoreUris()) {
            if (matcher.match(ignoreUrl, uri)) {
                return true;
            }
        }
        return false;
    }

    private void printError(HttpServletRequest request, HttpServletResponse response, Exception e) {

        log.error(e.getMessage(), e);

        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

        Integer code = 50000;
        String msg;

        if (e instanceof CommonException ce) {
            code = ce.getCode();
            msg = e.getMessage();
        } else {
            msg = "系统异常";
        }

        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.write(String.format(Constant.RESULT_JSON, code, msg));
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

}
