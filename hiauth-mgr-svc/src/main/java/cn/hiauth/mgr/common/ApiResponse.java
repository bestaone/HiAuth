package cn.hiauth.mgr.common;

public class ApiResponse<T> {

    private Integer code;
    private T data;
    private String msg;

    /**
     * 禁止new，全部使用静态方法
     */
    private ApiResponse() { }

    private ApiResponse(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    private ApiResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private ApiResponse(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ApiResponse success() {
        return new ApiResponse(BizCode.OK, null);
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse(BizCode.OK, data);
    }

    public static ApiResponse fail(Integer code, String msg) {
        return new ApiResponse<>(code, msg);
    }

    public static <T> ApiResponse<T> fail(Integer code, String msg, T data) {
        return new ApiResponse(code, msg, data);
    }

    public static ApiResponse fail(String msg) {
        return new ApiResponse(BizCode.UN_ERROR, msg);
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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
