package com.xyq.action;

import com.xyq.action.adapter.AbstractActionAdapter;
import com.xyq.service.IActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author xyq
 * @create 2019-09-15 14:14
 */
@Controller
@RequestMapping("/pages/action/*")
public class ActionAction extends AbstractActionAdapter {
    @Autowired
    private IActionService actionService;

    @RequestMapping("list")
    public ModelAndView list(int gid, HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        try {
            if (isAuth(6,request)){
                mav.addObject("allActions",actionService.listByGroups(getEid(request),gid));
                mav.setViewName(getMsg("action.list.page"));
            }else{
                mav.setViewName(getMsg("errors.page"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mav;
    }
}
