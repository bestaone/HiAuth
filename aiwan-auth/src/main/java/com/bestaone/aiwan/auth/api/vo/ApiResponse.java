package com.bestaone.aiwan.auth.api.vo;

import com.bestaone.aiwan.auth.exception.CommonException;

public class ApiResponse<T> {

    private Integer code;
    private T data;
    private String message;

    /**
     * 禁止new，全部使用静态方法
     */
    private ApiResponse(){}

    private ApiResponse(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    private ApiResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private ApiResponse(Integer code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public static ApiResponse sucess() {
        return new ApiResponse(10000, null);
    }

    public static <T> ApiResponse<T> sucess(T data) {
        return new ApiResponse<T>(10000, data);
    }

//    public static ApiResponse fail(Integer code) {
//        return new ApiResponse(code, null);
//    }

    public static ApiResponse fail(Integer code, String message) {
        return new ApiResponse<>(code, message);
    }

    public static <T> ApiResponse<T> fail(Integer code, T data, String message) {
        return new ApiResponse<T>(code, data, message);
    }

    public static ApiResponse fail(String message) {
        return new ApiResponse(50000, message);
    }

    public static <T> ApiResponse<T> fail(CommonException ex) {
        return new ApiResponse<>(ex.getCode(), ex.getMessage());
    }

    /////////////////////////////

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
