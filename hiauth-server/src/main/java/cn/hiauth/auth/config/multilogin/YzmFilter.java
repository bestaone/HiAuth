package cn.hiauth.auth.config.multilogin;

import cn.hiauth.auth.utils.CacheUtil;
import cn.hiauth.auth.utils.Constant;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.util.AntPathMatcher;

import java.io.IOException;

/**
 * 改进,使用只验证一次的过滤器 extends OncePerRequestFilter implements InitializingBean
 */
public class YzmFilter extends AbstractAuthenticationProcessingFilter {

    private static final String FORM_CAPTCHA_KEY = "yzm";

    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    private final String processUrl;

    private CacheUtil cacheUtil;

    public YzmFilter(String processUrl, String failureUrl) {
        super(processUrl);
        this.processUrl = processUrl;
        setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler(failureUrl));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res=(HttpServletResponse)response;

        if(pathMatcher.match(processUrl, req.getServletPath()) && HttpMethod.POST.name().equalsIgnoreCase(req.getMethod())){
            String formToken = request.getParameter(Constant.REQUEST_KEY_FORM_TOKEN);
            String imgCodeKey = Constant.CACHE_KEY_IMAGE_YZM + ":" + formToken;
            Object attr =  cacheUtil.get(imgCodeKey);

            if(attr==null){
                unsuccessfulAuthentication(req, res, new InsufficientAuthenticationException("图形验证码错误，请重新输入"));
                return;
            }

            String expect = attr.toString();
            if (expect != null && !expect.equals(req.getParameter(FORM_CAPTCHA_KEY))){
                unsuccessfulAuthentication(req, res, new InsufficientAuthenticationException("图形验证码错误，请重新输入"));
                return;
            }
            //销毁图形验证码，以免别人使用次图像验证码刷接口
            cacheUtil.expire(imgCodeKey, 0);
        }

        chain.doFilter(request, response);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException {
        return null;
    }

    public CacheUtil getCacheUtil() {
        return cacheUtil;
    }

    public void setCacheUtil(CacheUtil cacheUtil) {
        this.cacheUtil = cacheUtil;
    }

}
