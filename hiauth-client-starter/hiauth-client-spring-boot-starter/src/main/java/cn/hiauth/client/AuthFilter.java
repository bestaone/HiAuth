package cn.hiauth.client;

import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWT;
import cn.webestar.scms.commons.Assert;
import cn.webestar.scms.commons.CommonException;
import cn.webestar.scms.commons.SysCode;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * @author zgs
 */
@Slf4j
public class AuthFilter implements Filter {

    private final static String ERROR_RESULT = "{ \"code\": %d, \"message\": \"%s\" }";

    private final AntPathMatcher matcher = new AntPathMatcher();

    private final RedisTemplate<String, String> redisTemplate;

    private final HiAuthClientProperties properties;

    public AuthFilter(HiAuthClientProperties properties, RedisTemplate<String, String> redisTemplate) {
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

//        String cid = request.getHeader("_CID_");
//        if(StringUtils.hasText(cid)){
//            RequestContext context = new RequestContext();
//            context.setCid(Long.parseLong(cid));
//            ApiRequestContextHolder.setContext(context);
//        } else {
//            ApiRequestContextHolder.setContext(null);
//        }

        if (Constant.IGNORE_METHOD.equalsIgnoreCase(request.getMethod())) {
            chain.doFilter(request, response);
        } else if (matcherUrl(request.getRequestURI())) {
            SessionContext context = getSessionContext(request);
            Assert.notNull(context, 10401, "request fail");
            SessionContextHolder.setContext(context);
            checkPermissions(request, context.getAuth().getAuthorities());
            chain.doFilter(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    private void checkPermissions(HttpServletRequest request, List<Map<String, String>> authorities) {
        //是否开启了权限检查
        if (!properties.isCheckPermission()) {
            return;
        }
        String uri = request.getRequestURI();
        //检查是否为无需权限的api
        for (String api : properties.getIgnorePermissionUris()) {
            if (StringUtils.hasText(api) && StringUtils.hasText(uri) && matcher.match(api, uri)) {
                return;
            }
        }
        //检查是否分配了权限
        for (Map<String, String> o : authorities) {
            String api = o.get("api");
            if (StringUtils.hasText(api) && StringUtils.hasText(uri) && matcher.match(api, uri)) {
                return;
            }
        }
        throw new CommonException(SysCode.FORBIDDEN.getCode(), "没有权限");
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
            out.write(String.format(ERROR_RESULT, code, msg));
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
