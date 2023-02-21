package cn.hiauth.mgr.config.security;

import cn.hiauth.mgr.common.Assert;
import cn.hiauth.mgr.common.CommonException;
import cn.hiauth.mgr.utils.CacheUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    private final ObjectMapper objectMapper;
    private final CacheUtil cacheUtil;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, ObjectMapper objectMapper, CacheUtil cacheUtil) {
        super(authenticationManager);
        this.objectMapper = objectMapper;
        this.cacheUtil = cacheUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) {

        try{
            String authHeader = request.getHeader("Authorization");
            Assert.notEmpty(authHeader, 40001, "miss token.");
            Assert.isTrue(authHeader.startsWith("Bearer "), 40001, "miss Bearer.");
            String token = authHeader.replace("Bearer ", "");
            UsernamePasswordAuthenticationToken authentication = getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);
        } catch (CommonException e){
            write(response, e);
        } catch (ServletException e) {
            write(response, new CommonException(40000, "服务异常"));
        } catch (IOException e) {
            write(response, new CommonException(40000, "网络异常"));
        }

    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        UserContent userContent = (UserContent) cacheUtil.get(SecurityConstant.ACCESS_TOKEN_KEY + token);
        Assert.notNull(userContent, 40001, "invalid token.");
        Set<GrantedAuthority> authoritys = new HashSet<>();
        if(userContent.getPermissions()!=null){
            userContent.getPermissions().stream().forEach( e-> authoritys.add(new SimpleGrantedAuthority(e)) );
        }
        return new UsernamePasswordAuthenticationToken(userContent, null, authoritys);
    }

    private void write(HttpServletResponse response, CommonException e){
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.write(String.format(SecurityConstant.API_RESULT, e.getCode(), e.getMessage()));
            out.flush();
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
