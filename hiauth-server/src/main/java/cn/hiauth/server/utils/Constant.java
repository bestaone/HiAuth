package cn.hiauth.server.utils;

public class Constant {

    /**
     * 登录时存储图形验证码到缓存中的key
     */
    public static final String LOGIN_ACTION = "/auth/doLogin";

    /**
     * 登录时存储图形验证码到缓存中的key
     */
    public static final String CACHE_KEY_CAPTCHA = "captcha";
    /**
     * 登录时存储短信验证码到缓存中的key
     */
    public static final String CACHE_KEY_SMS_CODE = "smsCode";

    /**
     * 提交表单时request key
     */
    public static final String REQUEST_KEY_FORM_TOKEN = "formToken";

    public final static String IMG_API = "/unpapi/image/";

    /**
     * 默认的用户头像
     */
    public final static String USER_DEFAULT_AVATAR = "/user/default/avatar.jpeg";

}
