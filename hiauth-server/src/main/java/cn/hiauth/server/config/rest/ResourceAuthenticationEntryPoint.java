package cn.hiauth.server.config.rest;

import cn.webestar.scms.commons.SysCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.io.PrintWriter;

public class ResourceAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final static String ERROR_RESULT = "{ \"code\": %d, \"message\": \"%s\" }";

    public static void printError(HttpServletResponse response, String msg) {

        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

        Integer code = SysCode.ERROR.getCode();

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
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        printError(response, "令牌无效或已过期");

//        if (authException instanceof InsufficientAuthenticationException) {
//            String accept = request.getHeader("accept");
//            if (accept.contains(MediaType.TEXT_HTML_VALUE)) {
//                //如果是html请求类型，则返回登录页
//                LoginUrlAuthenticationEntryPoint loginUrlAuthenticationEntryPoint = new LoginUrlAuthenticationEntryPoint("/login");
//                loginUrlAuthenticationEntryPoint.commence(request, response, authException);
//            } else {
//                printError(response, "需要带上令牌进行访问");
//            }
//        } else if (authException instanceof InvalidBearerTokenException) {
//            printError(response, "令牌无效或已过期");
//        } else {
//            printError(response, authException.getMessage());
//        }
    }

}
