package com.bestaone.aiwan.core.exception;

import com.bestaone.aiwan.common.exception.CommonException;

public class Assert {

    public static void notNull(Object obj, Integer errorCode, String errorMsg) throws CommonException {
        if(obj==null){
            throwCommonException(errorCode, errorMsg);
        }
    }

//    public static void notNull(Object obj, String errorMsg, Class clazz) throws CommonException {
//        if(obj==null){
//            throwArgumentException(errorMsg, clazz);
//        }
//    }

    public static void isTrue(boolean expression, Integer errorCode, String errorMsg) throws CommonException {
        if(expression){
            throwCommonException(errorCode, errorMsg);
        }
    }

//    public static void isTrue(boolean expression, Integer errorCode, String errorMsg, Class clazz) throws CommonException {
//        if(expression){
//            throwArgumentException(errorMsg, clazz);
//        }
//    }

    private static void throwCommonException(Integer errorCode, String errorMsg) throws CommonException{
        throw new CommonException(errorCode, errorMsg);
    }

//    private static void throwArgumentException(String errorMsg, Class<CommonException> clazz) throws CommonException {
//        CommonException ex = null;
//        try {
//            ex = clazz.newInstance();
//            ex.setMsg(errorMsg);
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//
//        throw ex;
//    }

}
