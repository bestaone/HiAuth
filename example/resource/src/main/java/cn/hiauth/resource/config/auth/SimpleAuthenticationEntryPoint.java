package cn.hiauth.resource.config.auth;

import cn.hiauth.resource.utils.ResponseTools;
import cn.webestar.scms.commons.R;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import java.io.IOException;

public class SimpleAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if(exception instanceof InsufficientAuthenticationException){
            String accept = request.getHeader("accept");
            if(accept.contains(MediaType.TEXT_HTML_VALUE)){
                //如果是html请求类型，则返回登录页
                LoginUrlAuthenticationEntryPoint loginUrlAuthenticationEntryPoint = new LoginUrlAuthenticationEntryPoint("/login");
                loginUrlAuthenticationEntryPoint.commence(request,response,exception);
            }else {
                //如果是api请求类型，则返回json
                ResponseTools.write(response, R.fail(10401,"缺少访问令牌"));
            }
        }else if(exception instanceof InvalidBearerTokenException){
            ResponseTools.write(response, R.fail(10401,"令牌无效或过期"));
        }else{
            ResponseTools.write(response, R.fail(10401,exception.getMessage()));
        }
    }

}
