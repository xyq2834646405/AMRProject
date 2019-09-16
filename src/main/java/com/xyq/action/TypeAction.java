package com.xyq.action;

import com.xyq.action.adapter.AbstractActionAdapter;
import com.xyq.dao.ISubtypeDao;
import com.xyq.service.ISubtypeService;
import com.xyq.service.ITypeService;
import com.xyq.vo.Subtype;
import com.xyq.vo.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author xyq
 * @create 2019-09-16 10:48
 */
@Controller
@RequestMapping("/pages/type/*")
public class TypeAction extends AbstractActionAdapter {
    @Autowired
    private ITypeService typeService;
    @Autowired
    private ISubtypeService subtypeService;

    @RequestMapping("edit")
    public ModelAndView edit(Type vo, HttpServletRequest request, HttpServletResponse response){
        try {
            if (isAuth(9,request)){
                print(response,typeService.edit(vo,getEid(request)));
            }else {
                print(response,false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("list")
    public ModelAndView list(){
        ModelAndView mav = new ModelAndView();
        try {
            mav.addObject("allTypes",typeService.list());
            mav.setViewName(getMsg("type.list.page"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mav;
    }

    @RequestMapping("editSubtype")
    public ModelAndView editSubtype(Subtype vo,HttpServletRequest request,HttpServletResponse response){
        try {
            if (isAuth(10,request)){
                print(response,subtypeService.edit(vo,getEid(request)));
            }else{
                print(response,false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("listSubtype")
    public ModelAndView listSubtype(int tid){
        ModelAndView mav = new ModelAndView();
        try {
            mav.addObject("allSubtypes",subtypeService.list(tid));
            mav.setViewName(getMsg("subtype.list.page"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mav;
    }
}
