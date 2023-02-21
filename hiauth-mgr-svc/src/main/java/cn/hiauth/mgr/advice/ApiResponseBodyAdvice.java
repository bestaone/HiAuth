package cn.hiauth.mgr.advice;

import cn.hiauth.mgr.common.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Collection;
import java.util.Collections;

/**
 * 返回体统一封装器
 *
 * @author n1
 */
@Slf4j
@RestControllerAdvice(basePackages = "cn.hiauth.mgr.controller")
public class ApiResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean supports(MethodParameter returnType,@NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        return !returnType.hasMethodAnnotation(IgnoreResponseBody.class);
    }

    @SneakyThrows
    @SuppressWarnings("unchecked")
    @Override
    public Object beforeBodyWrite(Object body,
                                  @NonNull MethodParameter returnType,
                                  @NonNull MediaType selectedContentType,
                                  @NonNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  @NonNull ServerHttpRequest request,
                                  @NonNull ServerHttpResponse response) {

        response.getHeaders().set("Content-Type", "application/json");

        ApiResponse<Object> objectRest;
        if (body == null) {
            objectRest = ApiResponse.success(Collections.emptyMap());
        } else if (ApiResponse.class.isAssignableFrom(body.getClass())) {
            objectRest = (ApiResponse<Object>) body;
        }
        else if (checkPrimitive(body)) {
            // String 类型的会报错，要单独处理
            if(body instanceof String){
                return objectMapper.writeValueAsString(ApiResponse.success(body));
            } else {
                return ApiResponse.success(body);
            }
        }else {
            objectRest = ApiResponse.success(body);
        }
        return objectRest;
    }


    private boolean checkPrimitive(Object body) {
        Class<?> clazz = body.getClass();
        return clazz.isPrimitive()
                || clazz.isArray()
                || Collection.class.isAssignableFrom(clazz)
                || body instanceof Number
                || body instanceof Boolean
                || body instanceof Character
                || body instanceof String;
    }
}
