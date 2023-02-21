package cn.hiauth.mgr.common;

public class Assert {

    public static void notNull(Object obj, Integer errorCode, String errorMsg) throws CommonException {
        if(obj==null){
            throwCommonException(errorCode, errorMsg);
        }
    }

    public static void notEmpty(String obj, Integer errorCode, String errorMsg) throws CommonException {
        if(obj==null || "".equals(obj.trim())){
            throwCommonException(errorCode, errorMsg);
        }
    }

    public static void isTrue(boolean expression, Integer errorCode, String errorMsg) throws CommonException {
        if(!expression){
            throwCommonException(errorCode, errorMsg);
        }
    }

    private static void throwCommonException(Integer errorCode, String errorMsg) throws CommonException {
        throw new CommonException(errorCode, errorMsg);
    }

}
