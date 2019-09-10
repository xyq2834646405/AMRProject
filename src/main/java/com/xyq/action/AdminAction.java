package com.xyq.action;

import com.xyq.action.adapter.AbstractActionAdapter;
import com.xyq.service.IAdminService;
import com.xyq.util.MD5Code;
import com.xyq.vo.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author xyq
 * @create 2019-09-10 10:35
 */
@Controller
@RequestMapping("/pages/admin/*")
public class AdminAction extends AbstractActionAdapter {

    @Autowired
    private IAdminService adminService;

    @RequestMapping("addPre")
    public ModelAndView addPre(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        if (isAuth(1,request)) {
            try {
                Map<String, Object> map = adminService.addPre();
                mav.addObject("allLevels",map.get("allLevels"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            mav.setViewName(getMsg("admin.add.page"));
        }else{
            mav.setViewName(getMsg("errors.page"));
        }
        return mav;
    }

    @RequestMapping("add")
    public ModelAndView add(Emp vo, MultipartFile pic,HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        if(isAuth(2,request)){
            vo.setPhoto(createSingleFileName(pic));//创建文件名字
            vo.setHeid(getEid(request));//取得创建者的编号
            vo.setPassword(new MD5Code().getMD5ofStr(vo.getPassword()));//密码加密
            mav.setViewName(getMsg("forward.page"));
            try {
                if(adminService.add(vo)){
                    saveUpdateFile(pic,request,vo.getPhoto());//保存文件
                    setMsgAndUrl("vo.add.success","admin.add.action",mav);
                }else {
                    setMsgAndUrl("vo.add.failure","admin.add.action",mav);
                }
            } catch (Exception e) {
                setMsgAndUrl("vo.add.failure","admin.add.action",mav);
                e.printStackTrace();
            }
        }else{
            mav.setViewName(getMsg("errors.page"));
        }
        return mav;
    }

    @Override
    public String getFlag() {
        return "管理员";
    }

    @Override
    public String getSaveFileDiv() {
        return "/upload/emp/";
    }
}
