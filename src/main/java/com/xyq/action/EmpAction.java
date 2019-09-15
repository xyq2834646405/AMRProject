package com.xyq.action;

import com.xyq.action.adapter.AbstractActionAdapter;
import com.xyq.service.IEmpService;
import com.xyq.util.MD5Code;
import com.xyq.util.SplitUtil;
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
 * @create 2019-09-15 17:04
 */
@Controller
@RequestMapping("/pages/emp/*")
public class EmpAction extends AbstractActionAdapter {
    @Autowired
    private IEmpService empService;

    @RequestMapping("addPre")
    public ModelAndView addPre(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        if (isAuth(11,request)){
            try {
                Map<String, Object> map = empService.addPre();
                mav.addObject("allLevels",map.get("allLevels"));
                mav.addObject("allDepts",map.get("allDepts"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            mav.setViewName(getMsg("emp.add.page"));
        }else{
            mav.setViewName(getMsg("errors.page"));
        }
        return mav;
    }

    @RequestMapping("add")
    public ModelAndView add(Emp vo, MultipartFile pic, HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        if(isAuth(12,request)){
            vo.setPhoto(createSingleFileName(pic));//创建文件名字
            vo.setHeid(getEid(request));//取得创建者的编号
            vo.setPassword(new MD5Code().getMD5ofStr(vo.getPassword()));//密码加密
            mav.setViewName(getMsg("forward.page"));
            try {
                if(empService.add(vo,getEid(request))){
                    saveUpdateFile(pic,request,vo.getPhoto());//保存文件
                    setMsgAndUrl("vo.add.success","emp.add.action",mav);
                }else {
                    setMsgAndUrl("vo.add.failure","emp.add.action",mav);
                }
            } catch (Exception e) {
                setMsgAndUrl("vo.add.failure","emp.add.action",mav);
                e.printStackTrace();
            }
        }else{
            mav.setViewName(getMsg("errors.page"));
        }
        return mav;
    }

    @RequestMapping("editPre")
    public ModelAndView editPre(int eid){
        ModelAndView mav = new ModelAndView();
        try {
            Map<String, Object> map = empService.editPre(eid);
            mav.addObject("allDepts",map.get("allDepts"));
            mav.addObject("allLevels",map.get("allLevels"));
            mav.addObject("emp",map.get("emp"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        mav.setViewName(getMsg("emp.edit.page"));
        return mav;
    }

    @RequestMapping("edit")
    public ModelAndView edit(Emp vo,MultipartFile pic,HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        if (isAuth(15,request)){
            if (pic!=null&&pic.getSize()>0){
                vo.setPhoto(createSingleFileName(pic));
            }
            if (vo.getPassword()==null||"".equals(vo.getPassword())){
                vo.setPassword(null);
            }else{
                vo.setPassword(new MD5Code().getMD5ofStr(vo.getPassword()));
            }
            mav.setViewName(getMsg("forward.page"));
            try {
                if (empService.edit(vo,getEid(request))){
                    saveUpdateFile(pic,request,vo.getPhoto());
                    setMsgAndUrl("vo.edit.success","emp.list.action",mav);
                }else{
                    setMsgAndUrl("vo.edit.failure","emp.list.action",mav);
                }
            } catch (Exception e) {
                setMsgAndUrl("vo.edit.failure","emp.list.action",mav);
                e.printStackTrace();
            }
        }else{
            setMsgAndUrl("vo.edit.failure","emp.list.action",mav);
        }
        return mav;
    }

    @RequestMapping("list")
    public ModelAndView list(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        if (isAuth(13,request)) {
            SplitUtil split = new SplitUtil(this);
            handleSplit(request,split);
            try {
                Map<String, Object> map = empService.list(getEid(request),split.getColumn(), "%"+split.getKeyWord()+"%", split.getCurrentPage(), split.getLineSize());
                mav.addObject("allEmps",map.get("allEmps"));
                split.setAttribute(request,map.get("EmpCount"),"emp.list.action");
            } catch (Exception e) {
                e.printStackTrace();
            }
            mav.setViewName(getMsg("emp.list.page"));
        }else{
            mav.setViewName(getMsg("errors.page"));
        }
        return mav;
    }

    public String getFlag() {
        return "雇员";
    }

    public String getSaveFileDiv() {
        return "/upload/emp/";
    }

    public String getDefaultColumn() {
        return "name";
    }

    public String getColumnData() {
        return "雇员姓名:name|雇员编号:eid|雇员电话:phone";
    }
}
