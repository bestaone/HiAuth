package cn.hiauth.server.config.rest.security;

import cn.webestar.scms.commons.Assert;
import cn.webestar.scms.commons.CommonException;
import cn.webestar.scms.commons.SysCode;
import cn.webestar.scms.security.Constant;
import cn.webestar.scms.security.SecurityUser;
import cn.webestar.scms.security.SessionContextHolder;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author zgs
 */
@Slf4j
public class ReadonlyFilter implements Filter {

    public final static List<String> EDIT_KEY = List.of("/**/*create*", "/**/*update*", "/**/*delete*", "/**/*upload*");
    private final static String ERROR_RESULT = "{ \"code\": %d, \"message\": \"%s\" }";
    private final AntPathMatcher matcher = new AntPathMatcher();
    public String[] readonlyAccount;

    public ReadonlyFilter(String[] readonlyAccount) {
        this.readonlyAccount = readonlyAccount;
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
            return;
        }

        SecurityUser securityUser = SessionContextHolder.getPrincipal();
        if (securityUser == null) {
            chain.doFilter(request, response);
            return;
        }

        String username = securityUser.getUsername();
        if (username == null) {
            chain.doFilter(request, response);
            return;
        }

        boolean matcherAccount = matcherAccount(username);
        if (matcherAccount) {
            boolean readOnly = readOnlyUrl(request.getRequestURI());
            Assert.isTrue(readOnly, SysCode.ERROR.getCode(), "此账号为只读账号，不能修改数据");
        }
        chain.doFilter(request, response);
    }

    public boolean matcherAccount(String username) {
        for (String account : readonlyAccount) {
            if (matcher.match(account, username)) {
                return true;
            }
        }
        return false;
    }

    public boolean readOnlyUrl(String uri) {
        for (String key : EDIT_KEY) {
            if (matcher.match(key, uri)) {
                return false;
            }
        }
        return true;
    }

    private void printError(HttpServletRequest request, HttpServletResponse response, Exception e) {
        log.error(e.getMessage(), e);
        Integer code = SysCode.ERROR.getCode();
        String msg;
        if (e instanceof CommonException ce) {
            code = ce.getCode();
            msg = e.getMessage();
        } else {
            msg = "系统异常";
        }
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
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
