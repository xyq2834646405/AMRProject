package com.xyq.action;

import com.xyq.action.adapter.AbstractActionAdapter;
import com.xyq.service.IPurchaseService;
import com.xyq.util.SplitUtil;
import com.xyq.vo.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Author xyq
 * @create 2019-09-17 20:53
 */
@Controller
@RequestMapping("/pages/purchase/*")
public class PurchaseAction extends AbstractActionAdapter {
    @Autowired
    private IPurchaseService purchaseService;

    @RequestMapping("add")
    public ModelAndView add(Purchase purchase,HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        if (isAuth(30,request)){
            try {
                if (purchaseService.add(getEid(request),purchase)){
                    setMsgAndUrl("vo.add.success","purchase.apply.page",mav);
                }else {
                    setMsgAndUrl("vo.add.failure", "purchase.apply.page", mav);
                }
            } catch (Exception e) {
                e.printStackTrace();
                setMsgAndUrl("vo.add.failure", "purchase.apply.page", mav);
            }
            mav.setViewName(getMsg("forward.page"));
        }else{
            mav.setViewName(getMsg("errors.page"));
        }
        return mav;
    }

    @RequestMapping("apply")
    public ModelAndView apply(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        if(isAuth(27,request)){
            SplitUtil split = new SplitUtil(this);
            handleSplit(request,split);
            try {
                Map<String, Object> map = purchaseService.listByEmp(getEid(request), split.getCurrentPage(), split.getLineSize());
                mav.addObject("allPurchases",map.get("allPurchases"));
                split.setAttribute(request,map.get("purchaseCount"),"purchase.apply.action");
            } catch (Exception e) {
                e.printStackTrace();
            }
            mav.setViewName(getMsg("purchase.apply.page"));
        }else{
            mav.setViewName(getMsg("errors.page"));
        }
        return mav;
    }

    @RequestMapping("show")
    public ModelAndView show(int pid,HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        if(isAuth(27,request)){
            try {
                mav.addObject("purchase",purchaseService.getByEmp(getEid(request),pid));
            } catch (Exception e) {
                e.printStackTrace();
            }
            mav.setViewName(getMsg("purchase.show.page"));
        }else{
            mav.setViewName(getMsg("errors.page"));
        }
        return mav;
    }

    @RequestMapping("list")
    public ModelAndView list(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        if (isAuth(41,request)){
            SplitUtil split = new SplitUtil(this);
            handleSplit(request,split);
            try {
                Map<String, Object> map = purchaseService.listSimple(getEid(request), split.getCurrentPage(), split.getLineSize());
                mav.addObject("allPurchases",map.get("allPurchases"));
                split.setAttribute(request,map.get("purchaseCount"),"purchase.list.action");
            } catch (Exception e) {
                e.printStackTrace();
            }
            mav.setViewName(getMsg("purchase.list.page"));
        }else {
            mav.setViewName(getMsg("errors.page"));
        }
        return mav;
    }

    @RequestMapping("detailsShow")
    public ModelAndView detailsShow(int pid,HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        if (isAuth(41,request)){
            try {
                mav.addObject("purchase",purchaseService.show(getEid(request),pid));
            } catch (Exception e) {
                e.printStackTrace();
            }
            mav.setViewName(getMsg("purchase.show.page"));
        }else{
            mav.setViewName(getMsg("errors.page"));
        }
        return mav;
    }

    @RequestMapping("audit")
    public ModelAndView audit(int pid, int status, HttpServletRequest request, HttpServletResponse response){
        if (isAuth(42,request)){
            try {
                print(response,purchaseService.editStatus(getEid(request),pid,status));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            print(response,false);
        }
        return null;
    }

    @Override
    public String getFlag() {
        return "购入商品";
    }
}
