package com.xyq.action;

import com.xyq.action.adapter.AbstractActionAdapter;
import com.xyq.service.ITakeService;
import com.xyq.util.SplitUtil;
import com.xyq.vo.Take;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author xyq
 * @create 2019-09-19 21:42
 */
@Controller
@RequestMapping("/pages/res/*")
public class TakeAction extends AbstractActionAdapter {
    @Autowired
    private ITakeService takeService;

    @RequestMapping("addTake")
    public ModelAndView addTake(Take vo, HttpServletRequest request, HttpServletResponse response){
        try {
            print(response,takeService.add(getEid(request),vo));
        } catch (Exception e) {
            e.printStackTrace();
            print(response,false);
        }
        return null;
    }

    @RequestMapping("preget")
    public ModelAndView preget(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        try {
            Map<String, Object> map = takeService.listUnGet(getEid(request));
            mav.addObject("allRess",map.get("allRess"));
            mav.addObject("allTakes",map.get("allTakes"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        mav.setViewName(getMsg("res.preget.list.page"));
        return mav;
    }

    @RequestMapping("editTake")
    public ModelAndView editTake(String data,HttpServletRequest request,HttpServletResponse response){
        String[] result = data.split("\\|");
        Map<Integer,Integer> map = new HashMap<Integer, Integer>();
        for (int x = 0; x < result.length; x++) {
            String[] temp = result[x].split(":");
            map.put(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]));
        }
        try {
            print(response,takeService.edit(getEid(request),map));
        } catch (Exception e) {
            e.printStackTrace();
            print(response,false);
        }
        return null;
    }

    @RequestMapping("removeTake")
    public ModelAndView removeTake(String data,HttpServletRequest request,HttpServletResponse response){
        String[] result = data.split("\\|");
        Set<Integer> set = new HashSet<Integer>();
        for (int x = 0; x < result.length; x++) {
            set.add(Integer.parseInt(result[x]));
        }
        try {
            print(response,takeService.remove(getEid(request),set));
        } catch (Exception e) {
            e.printStackTrace();
            print(response,false);
        }
        return null;
    }

    @RequestMapping("get")
    public ModelAndView get(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        try {
            if (takeService.editSubmit(getEid(request))){
                setMsgAndUrl("res.get.success","res.emp.list.action",mav);
            }else{
                setMsgAndUrl("res.get.failure","res.emp.list.action",mav);
            }
        } catch (Exception e) {
            setMsgAndUrl("res.get.failure","res.emp.list.action",mav);
            e.printStackTrace();
        }
        mav.setViewName(getMsg("forward.page"));
        return mav;
    }

    @RequestMapping("emp_list")
    public ModelAndView empList(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        SplitUtil split = new SplitUtil(this);
        handleSplit(request,split);
        try {
            Map<String, Object> map = takeService.listByEmp(getEid(request), split.getCurrentPage(), split.getLineSize());
            split.setAttribute(request,map.get("takeCount"),"emp.list.action");
            mav.addObject("allTakes",map.get("allTakes"));
            mav.addObject("resMap",map.get("resMap"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        mav.setViewName(getMsg("res.emp.list.page"));
        return mav;
    }

    @RequestMapping("audit")
    public ModelAndView audit(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        SplitUtil split = new SplitUtil(this);
        handleSplit(request,split);
        try {
            Map<String, Object> map = takeService.list(getEid(request), split.getColumn(), split.getKeyWord(), split.getCurrentPage(), split.getLineSize());
            split.setAttribute(request,map.get("takeCount"),"res.audit.list.action");
            mav.addObject("allTakes",map.get("allTakes"));
            mav.addObject("resMap",map.get("resMap"));
        }catch (Exception e){
            e.printStackTrace();
        }
        mav.setViewName(getMsg("res.audit.list.page"));
        return mav;
    }

    @RequestMapping("editAudit")
    public ModelAndView editAudit(Take take,HttpServletRequest request,HttpServletResponse response){
        if (isAuth(28,request)){
            try {
                print(response,takeService.editAudit(getEid(request),take));
            } catch (Exception e) {
                e.printStackTrace();
                print(response,false);
            }
        }else{
            print(response,false);
        }
        return null;
    }

    @RequestMapping("editRflag")
    public ModelAndView editRflag(int tkid,HttpServletRequest request,HttpServletResponse response){
        try {
            print(response,takeService.editRflag(getEid(request),tkid));
        } catch (Exception e) {
            e.printStackTrace();
            print(response,false);
        }
        return null;
    }

    @RequestMapping("editRdate")
    public ModelAndView editRdate(int tkid,HttpServletRequest request,HttpServletResponse response){
        if (isAuth(28,request)){
            try {
                print(response,takeService.editRdate(getEid(request),tkid));
            } catch (Exception e) {
                e.printStackTrace();
                print(response,false);
            }
        }else {
            print(response,false);
        }
        return null;
    }

    public String getDefaultColumn() {
        return "geid";
    }
}
