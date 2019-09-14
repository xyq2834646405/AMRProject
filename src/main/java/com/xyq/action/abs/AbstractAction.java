package com.xyq.action.abs;

import com.xyq.util.SplitUtil;
import com.xyq.vo.Action;
import com.xyq.vo.Emp;
import com.xyq.vo.Groups;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Locale;
import java.util.UUID;

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
     * 验证在session中是否存在有制定的权限数据
     * @param actid 权限编号,唯一的,不可变
     * @param request
     * @return
     */
    public boolean isAuth(int actid,HttpServletRequest request){
        Emp emp = getEmp(request);
        Iterator<Groups> iterGup = emp.getDept().getAllGroups().iterator();
        while (iterGup.hasNext()){
            Groups gup = iterGup.next();
            Iterator<Action> iterAct = gup.getAllActions().iterator();
            while(iterAct.hasNext()){
                Action act = iterAct.next();
                if (act.getActid().equals(actid)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 取得session中出现的登录操作
     * @param request
     * @return
     */
    public Emp getEmp(HttpServletRequest request){
        return (Emp) request.getSession().getAttribute("emp");
    }

    /**
     * 取得session中的雇员编号
     * @param request
     * @return
     */
    public Integer getEid(HttpServletRequest request){
        return getEmp(request).getEid();
    }

    /**
     * 文件的爆粗才能操作
     * @param file 包含所有的文件信息
     * @param request 取得绝对路径
     * @param fileName 文件名称
     * @return
     */
    public boolean saveUpdateFile(MultipartFile file,HttpServletRequest request,String fileName){
        String filePath = request.getServletContext().getRealPath(this.getSaveFileDiv())+fileName;
        if(file==null){
            System.out.println("*********************************88");
        }
        if (file.getSize() > 0) {
            OutputStream output = null;
            InputStream input = null;
            try {
                output = new FileOutputStream(filePath);
                input = file.getInputStream();
                byte data[] = new byte[2048];
                int temp = 0;
                while ((temp = input.read(data)) != -1) {
                    output.write(data, 0, temp);
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            } finally {
                if (output != null) {
                    try {
                        output.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return false;
    }

    /**
     * 取得文件名称,如果没有上传,则返回的是"nophoto.png"文件
     * @param file
     * @return
     */
    public String createSingleFileName(MultipartFile file){
        if (file == null) {
            return "nophoto.png";
        }
        if (file.getSize() <= 0) {
            return "nophoto.png";
        }
        return UUID.randomUUID() + "." + this.getFileExt(file.getContentType());
    }

    /**
     * 取得文件的后缀
     * @param contentType
     * @return
     */
    public String getFileExt(String contentType){
        if ("image/bmp".equals(contentType)) {
            return "bmp";
        } else if ("image/gif".equals(contentType)) {
            return "gif";
        } else if ("image/jpeg".equals(contentType)) {
            return "jpg";
        } else if ("image/png".equals(contentType)) {
            return "png";
        }
        return null;
    }

    /**
     * 处理分页设置的数据
     * @param request
     * @param split
     */
    public void handleSplit(HttpServletRequest request, SplitUtil split){
        split.setCp(request.getParameter("cp"));
        split.setLs(request.getParameter("ls"));
        split.setKw(request.getParameter("kw"));
        split.setCol(request.getParameter("col"));
        request.setAttribute("currentPage",split.getCurrentPage());
        request.setAttribute("lineSize",split.getLineSize());
        request.setAttribute("column",split.getColumn());
        request.setAttribute("keyWord",split.getKeyWord());
        request.setAttribute("columnData",getColumnData());
    }

    /**
     * 实现数据的输出操作
     * @param response
     * @param obj
     */
    public void print(HttpServletResponse response,Object obj){
        try {
            response.getWriter().print(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回crud除操作时的执行标记
     * @return
     */
    public abstract String getFlag();

    /**
     * 取得上传路径
     * @return
     */
    public abstract String getSaveFileDiv();

    /**
     * 得到分页默认列
     * @return
     */
    public abstract String getDefaultColumn();

    /**
     * 实现页面的模糊查询列表显示,格式"标签:字段|标签:字段"
     * @return
     */
    public abstract String getColumnData();
}
