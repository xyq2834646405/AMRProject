package com.xyq.action;

import com.xyq.action.abs.AbstractAction;
import com.xyq.action.adapter.AbstractActionAdapter;
import com.xyq.service.IEmpService;
import com.xyq.util.MD5Code;
import com.xyq.vo.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author xyq
 * @create 2019-09-09 15:34
 */
@Controller
public class LoginAction  extends AbstractActionAdapter {
    @Autowired
    private IEmpService empService;

    @RequestMapping("/logout")
    public ModelAndView logout(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        request.getSession().invalidate();
        setMsgAndUrl("logout.success","login.page",mav);
        mav.setViewName(getMsg("forward.page"));
        return mav;
    }

    @RequestMapping("/login")
    public ModelAndView login(Emp vo, HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        vo.setPassword(new MD5Code().getMD5ofStr(vo.getPassword()));
        try {
            if(empService.login(vo)){
                request.getSession().setAttribute("emp",vo);
                setMsgAndUrl("login.success","index.page",mav);
            }else {
                setMsgAndUrl("login.failure","login.page",mav);
            }
            mav.setViewName(getMsg("forward.page"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mav;
    }
}
