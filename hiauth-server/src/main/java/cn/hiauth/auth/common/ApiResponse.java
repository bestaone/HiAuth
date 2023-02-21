package cn.hiauth.auth.common;
public class ApiResponse<T> {
    private Integer code;
    private T data;
    private String message;

    /**
     * 禁止new，全部使用静态方法
     */
    private ApiResponse() { }

    private ApiResponse(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    private ApiResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private ApiResponse(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ApiResponse success() {
        return new ApiResponse(BizCode.OK, null);
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse(BizCode.OK, data);
    }

    public static ApiResponse fail(Integer code, String message) {
        return new ApiResponse<>(code, message);
    }

    public static <T> ApiResponse<T> fail(Integer code, String message, T data) {
        return new ApiResponse(code, message, data);
    }

    public static ApiResponse fail(String message) {
        return new ApiResponse(BizCode.UN_ERROR, message);
    }

    public static <T> ApiResponse<T> fail(CommonException ex) {
        return new ApiResponse(ex.getCode(), ex.getMessage());
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
