package com.xyq.util;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author xyq
 * @create 2019-09-09 17:33
 */
public class ValidatorRules {
    private Object obj ;
    private String rules[];
    private HttpServletRequest request;
    /**
     * @param obj 当前要执行操作的Action程序类
     * @param rules 所有的验证规则
     * @param request 进行参数的接收处理
     */
    public ValidatorRules(Object obj , String rules[], HttpServletRequest request) {
        this.obj = obj ;
        this.rules = rules;
        this.request = request;
    }

    public Map<String,String> validate(){
        Map<String,String> map = new HashMap<String,String>() ;
        for (int i = 0; i < rules.length; i++) {
            String[] temp = rules[i].split(":");
            String paramValue = request.getParameter(temp[0]);
            if(temp[1].equals("string")){
                if(!ValidatorUtil.isString(paramValue))
                    map.put(temp[0],MessageUtil.getMessage(obj,"invalidate.string.error.msg"));
            }else if (temp[1].equals("int")){
                if(!ValidatorUtil.isInt(paramValue))
                    map.put(temp[0],MessageUtil.getMessage(obj,"invalidate.int.error.msg"));
            }else if (temp[1].equals("double")){
                if(!ValidatorUtil.isDouble(paramValue))
                    map.put(temp[0],MessageUtil.getMessage(obj,"invalidate.double.error.msg"));
            }else if (temp[1].equals("date")){
                if(!ValidatorUtil.isDate(paramValue))
                    map.put(temp[0],MessageUtil.getMessage(obj,"invalidate.date.error.msg"));
            }else if (temp[1].equals("datetime")){
                if(!ValidatorUtil.isDatetime(paramValue))
                    map.put(temp[0],MessageUtil.getMessage(obj,"invalidate.datetime.error.msg"));
            }else if (temp[1].equals("rand")){
                if(!ValidatorUtil.isString(paramValue))
                    map.put(temp[0],MessageUtil.getMessage(obj,"invalidate.code.error.msg"));
            }
        }
        return map;
    }
}
