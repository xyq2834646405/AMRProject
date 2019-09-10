package com.xyq.interceptor;

import com.xyq.util.MessageUtil;
import com.xyq.util.MimeValidator;
import com.xyq.util.ValidatorRules;
import com.xyq.util.ValidatorUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Author xyq
 * @create 2019-09-09 16:41
 */
public class MyInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1、转换为handlerMethod类才可以知道操作的那个Action是哪个具体的操作方法
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        //2、拼凑读取资源文件的key的内容
        String validatorKey = handlerMethod.getBeanType().getSimpleName()+"."+handlerMethod.getMethod().getName()+".rules";
        //3、取得具体的验证规则
        String validatorContent = MessageUtil.getMessage(handlerMethod.getBean(), validatorKey);
        if(validatorContent==null||"".equals(validatorContent)){
            return true;
        }else{
            //4、验证
            String[] rules = validatorContent.split("\\|");
            Map<String, String> result = new ValidatorRules(handlerMethod.getBean(), rules, request).validate();
            if (result.size()>0){//有错误
                request.setAttribute("errors",result);
                request.getRequestDispatcher("/error.jsp").forward(request,response);
                return false;
            }else {
                boolean flag = MimeValidator.isMime(handlerMethod.getBean(), request);
                if (flag==false){
                    result.put("file",MessageUtil.getMessage(handlerMethod.getBean(),"invalidate.file.mime.error.msg"));
                    request.setAttribute("errors",result);
                    request.getRequestDispatcher("/error.jsp").forward(request,response);
                    return false;
                }
            }
        }
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
