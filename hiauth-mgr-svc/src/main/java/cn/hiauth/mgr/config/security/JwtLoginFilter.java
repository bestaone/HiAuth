package cn.hiauth.mgr.config.security;

import cn.hiauth.mgr.common.BizCode;
import cn.hiauth.mgr.utils.CacheUtil;
import cn.hiauth.mgr.utils.JwtTokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Slf4j
public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {

    private static final String LOGIN_RESULT = "{ \"code\": %d, \"data\": { \"accessToken\": \"%s\", \"refreshToken\": \"%s\", \"expire\": %d, \"userId\": %d } }";
    private static final String FORM_USERNAME_KEY = "username";
    private static final String FORM_PASSWORD_KEY = "password";
    private final boolean postOnly = true;

    private final ObjectMapper objectMapper;
    private final CacheUtil cacheUtil;

    public JwtLoginFilter(AuthenticationManager authenticationManager, ObjectMapper objectMapper, CacheUtil cacheUtil) {
        super("/api/login", authenticationManager);
        this.objectMapper = objectMapper;
        this.cacheUtil = cacheUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        Map<String,String> map;
        try {
            map = objectMapper.readValue(request.getInputStream(), Map.class);
        } catch (IOException e) {
            throw new AuthenticationServiceException(e.getMessage());
        }

        if(!map.containsKey(FORM_USERNAME_KEY) || !map.containsKey(FORM_PASSWORD_KEY)){
            throw new AuthenticationServiceException("参数错误.");
        }

        String username = map.get(FORM_USERNAME_KEY);
        String password = map.get(FORM_PASSWORD_KEY);

        Authentication authentication = null;
        try {
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
            // Allow subclasses to set the "details" property
            setDetails(request, authRequest);
            authentication = this.getAuthenticationManager().authenticate(authRequest);
        }catch (Exception e) {
            throw new AuthenticationServiceException(e.getMessage());
        }

        return authentication;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth){

        SimpleUserDetails userDetails = (SimpleUserDetails) auth.getPrincipal();
        UserContent userContent = new UserContent();
        userContent.setUserId(userDetails.getUserId());
        userContent.setUsername(userDetails.getUsername());
        userContent.setPhoneNum(userDetails.getPhoneNum());
        userContent.setAvatarUrl(userDetails.getAvatarUrl());

        if(userDetails.getAuthorities()!=null && userDetails.getAuthorities().size()>0){
            Set<String> permissions = new HashSet<>();
            userDetails.getAuthorities().forEach( e-> permissions.add(e.getAuthority()) );
            userContent.setPermissions(permissions);
        }

        String accessToken = JwtTokenUtil.generateToken(userContent.getUsername());
        String refreshToken = UUID.randomUUID().toString().replace("-", "");

        cacheUtil.set(SecurityConstant.ACCESS_TOKEN_KEY + accessToken, userContent, SecurityConstant.ACCESS_TOKEN_EXPIRE);
        cacheUtil.set(SecurityConstant.REFRESH_TOKEN_KEY + refreshToken, accessToken, SecurityConstant.REFRESH_TOKEN_EXPIRE);

        response.addHeader("Authorization", "Bearer " + accessToken);
        write(response, String.format(LOGIN_RESULT, BizCode.OK, accessToken, refreshToken, SecurityConstant.ACCESS_TOKEN_EXPIRE, userContent.getUserId()));

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        write(response, String.format(SecurityConstant.API_RESULT, 40003, "用户名或密码错误"));
//        super.unsuccessfulAuthentication(request, response, failed);
    }

    private void write(HttpServletResponse response, String str){
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.write(str);
            out.flush();
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    protected void setDetails(HttpServletRequest request, UsernamePasswordAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }

}
