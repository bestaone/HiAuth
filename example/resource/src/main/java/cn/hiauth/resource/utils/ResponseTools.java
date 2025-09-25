package cn.hiauth.resource.utils;

import cn.webestar.scms.commons.R;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ResponseTools {

    private static final ObjectMapper om = new ObjectMapper();

    static {
        om.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public static void write(HttpServletResponse response, R<?> r) throws IOException {
        // 设置响应头
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        // 序列化并写入响应
        try {
            String jsonResponse = om.writeValueAsString(r);
            response.getWriter().write(jsonResponse);
            response.getWriter().flush();
        } catch (IOException e) {
            // 如果JSON序列化失败，使用简单文本响应
            response.setContentType("text/plain;charset=UTF-8");
            response.getWriter().write("错误处理失败: " + e.getMessage());
        }
    }

}