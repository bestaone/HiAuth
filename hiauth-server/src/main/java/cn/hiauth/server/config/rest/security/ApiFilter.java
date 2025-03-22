package cn.hiauth.server.config.rest.security;//package cn.hiauth.server.config.apilogin;
//
//import jakarta.servlet.Filter;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletRequest;
//import jakarta.servlet.ServletResponse;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//
///**
// * @author zgs
// */
//@Slf4j
//public class ApiFilter implements Filter {
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) {
//        final HttpServletRequest request = (HttpServletRequest) servletRequest;
//        final HttpServletResponse response = (HttpServletResponse) servletResponse;
//        try {
//            chain.doFilter(request, response);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//}
