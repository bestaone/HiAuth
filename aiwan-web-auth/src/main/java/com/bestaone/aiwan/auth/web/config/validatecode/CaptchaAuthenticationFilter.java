package com.bestaone.aiwan.auth.web.config.validatecode;

import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.util.AntPathMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//改进,使用只验证一次的过滤器 extends OncePerRequestFilter implements InitializingBean
public class CaptchaAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private static final String FORM_CAPTCHA_KEY = "yzm";

    public static final String SESSION_CAPTCHA_KEY = "SESSION_YZM_KEY";

    private AntPathMatcher pathMatcher = new AntPathMatcher();

    private String processUrl;

    public CaptchaAuthenticationFilter(String processUrl, String failureUrl) {
        super(processUrl);
        this.processUrl = processUrl;
        setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler(failureUrl));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res=(HttpServletResponse)response;

        if(pathMatcher.match(processUrl, req.getServletPath()) && "POST".equalsIgnoreCase(req.getMethod())){
            Object attr = req.getSession().getAttribute(SESSION_CAPTCHA_KEY);
            if(attr==null){
                unsuccessfulAuthentication(req, res, new InsufficientAuthenticationException("验证码错误"));
                return;
            }
            //销毁验证码
            req.getSession().removeAttribute(FORM_CAPTCHA_KEY);

            String expect = attr.toString();
            if (expect != null && !expect.equals(req.getParameter(FORM_CAPTCHA_KEY))){
                unsuccessfulAuthentication(req, res, new InsufficientAuthenticationException("验证码错误"));
                return;
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        return null;
    }

}
