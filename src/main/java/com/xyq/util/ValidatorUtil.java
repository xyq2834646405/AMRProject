package com.xyq.util;

/**
 * @Author xyq
 * @create 2019-09-09 17:41
 */
public class ValidatorUtil {
    /**
     * string验证
     * @param str
     * @return
     */
    public static boolean isString(String str){
        if(str==null||"".equals(str))
            return false;
        return true;
    }

    /**
     * int验证
     * @param str
     * @return
     */
    public static boolean isInt(String str){
        if(isString(str))
            return str.matches("\\d+(\\.\\d+)?");
        return false;
    }

    /**
     * double验证
     * @param str
     * @return
     */
    public static boolean isDouble(String str){
        if(isString(str))
            return str.matches("\\d+(\\.\\d+)?");
        return false;
    }

    /**
     * data验证
     * @param str
     * @return
     */
    public static boolean isDate(String str){
        if (isString(str))
            return str.matches("\\d{4}-\\d{2}-\\d{2}");
        return false;
    }

    /**
     * datatime验证
     * @param str
     * @return
     */
    public static boolean isDatetime(String str){
        if (isString(str))
            return str.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}");
        return false;
    }

    /**
     * 文件验证
     * @param mimeRules
     * @param mime
     * @return
     */
    public static boolean isMime(String mimeRules[],String mime){
        if (isString(mime)){
            for (int i = 0; i < mimeRules.length; i++) {
                if(mime.equalsIgnoreCase(mimeRules[i]))
                    return true;
            }
        }
        return false;
    }
}
