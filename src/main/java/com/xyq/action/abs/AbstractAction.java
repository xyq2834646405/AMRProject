package com.xyq.action.abs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * @Author xyq
 * @create 2019-09-09 13:45
 */
public abstract class AbstractAction {
    @Autowired
    private MessageSource messageSource;

    /**
     * 时间转换器
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(java.util.Date.class,new CustomDateEditor(sdf,true));
    }

    /**
     * 设置所需要的msg与url数据
     * @param msgKey
     * @param urlKey
     * @param mav
     */
    public void setMsgAndUrl(String msgKey, String urlKey, ModelAndView mav){
        mav.addObject("msg",getMsg(msgKey));
        mav.addObject("url",getMsg(urlKey));
    }

    /**
     * 读取资源文件
     * @param key 资源文件的key
     * @return
     */
    public String getMsg(String key){
        try{
            return messageSource.getMessage(key,new Object[]{getFlag()}, Locale.getDefault());
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 传递任意多个参数
     * @param key
     * @param arg
     * @return
     */
    public String getMsg(String key,Object... arg){
        try{
            return messageSource.getMessage(key,arg, Locale.getDefault());
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 设置值进session
     * @param request
     */
    public void setSessionAttribute(HttpServletRequest request,String key,Object value){
        request.getSession().setAttribute(key,value);
    }

    /**
     * 返回crud除操作时的执行标记
     * @return
     */
    public abstract String getFlag();
}
