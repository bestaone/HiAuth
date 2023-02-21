package cn.hiauth.mgr.config.security;

import cn.hiauth.mgr.common.BizCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author: Guosheng.Zhang
 * @date: 2021/8/25
 */
public class DefaultLogoutHandler implements LogoutHandler {

    @Override
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse response, Authentication authentication) {
        SecurityContextHolder.getContext().setAuthentication(null);
        write(response, String.format(SecurityConstant.API_RESULT, BizCode.OK, "success"));
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

}
