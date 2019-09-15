package com.xyq.action;

import com.xyq.action.adapter.AbstractActionAdapter;
import com.xyq.service.IDeptService;
import com.xyq.vo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author xyq
 * @create 2019-09-15 11:24
 */
@Controller
@RequestMapping("/pages/dept/*")
public class DeptAction extends AbstractActionAdapter {
    @Autowired
    private IDeptService deptService;

    @RequestMapping("edit")
    public ModelAndView edit(Dept vo, HttpServletRequest request, HttpServletResponse response){
        try {
            if(isAuth(7,request)){
                print(response,deptService.edit(getEid(request),vo));
            }else {
                print(response,false);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/list")
    public ModelAndView list(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        try {
            if(isAuth(4,request)){
                mav.addObject("allDepts",deptService.list(getEid(request)));
                mav.setViewName(getMsg("dept.list.page"));
            }else {
                mav.setViewName(getMsg("errors.page"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return mav;
    }
}
