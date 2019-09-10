package com.xyq.util;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.Map;

/**
 * @Author xyq
 * @create 2019-09-09 18:58
 */
public class MimeValidator {
    public static boolean isMime(Object obj, HttpServletRequest request){
        //1、必须确定好是否有上传文件
        MultipartResolver mr = new CommonsMultipartResolver();
        if(mr.isMultipart(request)){//当前有上传文件
            //2、取得定义的mime验证规则
            String mimeContent = MessageUtil.getMessage(obj,"mimeType");
            if(mimeContent==null||"".equals(mimeContent)){
                return true;
            }else {
                String[] mimeRules = mimeContent.split("\\|");
                MultipartRequest mreq = (MultipartRequest) request;//处理上传的request
                Map<String, MultipartFile> map = mreq.getFileMap();//取得全部的上传内容
                if(map.size()>0){
                    Iterator<Map.Entry<String, MultipartFile>> iter = map.entrySet().iterator();
                    while(iter.hasNext()){
                        Map.Entry<String, MultipartFile> me = iter.next();
                        if(me.getValue().getSize()>0){
                            if(!ValidatorUtil.isMime(mimeRules,me.getValue().getContentType())){
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}
