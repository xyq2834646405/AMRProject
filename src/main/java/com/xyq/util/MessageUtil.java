package com.xyq.util;

import java.lang.reflect.Method;

/**
 * @Author xyq
 * @create 2019-09-09 17:03
 */
public class MessageUtil {
    public static String getMessage(Object obj,String key){
        try{
            Method msgSourceMethod = obj.getClass().getMethod("getMsg",String.class);
            return (String) msgSourceMethod.invoke(obj,key);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
