package com.xyq.action;

import com.xyq.action.adapter.AbstractActionAdapter;
import com.xyq.service.IGroupsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author xyq
 * @create 2019-09-15 13:51
 */
@Controller
@RequestMapping("/pages/groups/*")
public class GroupsAction extends AbstractActionAdapter {
    @Autowired
    private IGroupsService groupsService;

    @RequestMapping("list")
    public ModelAndView list(int did, HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        try{
            if (isAuth(6,request)){
                mav.addObject("allGroups",groupsService.listByDept(getEid(request),did));
                mav.setViewName(getMsg("groups.list.page"));
            }else {
                mav.setViewName(getMsg("errors.page"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return mav;
    }
}
