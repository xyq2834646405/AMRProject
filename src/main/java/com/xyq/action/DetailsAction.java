package com.xyq.action;

import com.xyq.action.adapter.AbstractActionAdapter;
import com.xyq.service.IDetailsService;
import com.xyq.vo.Details;
import com.xyq.vo.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @Author xyq
 * @create 2019-09-16 15:12
 */
@Controller
@RequestMapping("/pages/res/*")
public class DetailsAction extends AbstractActionAdapter {
    @Autowired
    private IDetailsService detailsService;

    @RequestMapping("addPre")
    public ModelAndView addPre(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        try {
            Map<String, Object> map = detailsService.addPre(getEid(request));
            mav.addObject("allTypes",map.get("allTypes"));
            mav.setViewName(getMsg("details.add.page"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mav;
    }

    @RequestMapping("add")
    public ModelAndView add(Details vo, MultipartFile pic,HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        if (isAuth(25,request)){
            vo.setPhoto(createSingleFileName(pic));
            try{
                if(detailsService.add(vo,getEid(request))){
                    saveUpdateFile(pic,request,vo.getPhoto());
                    setMsgAndUrl("vo.add.success","details.list.action",mav);
                }else {
                    setMsgAndUrl("vo.add.failure","details.list.action",mav);
                }
            }catch (Exception e){
                e.printStackTrace();
                setMsgAndUrl("vo.add.failure","details.list.action",mav);
            }
            mav.setViewName(getMsg("forward.page"));
        }else{
            mav.setViewName(getMsg("errors.page"));
        }
        return mav;
    }

    @RequestMapping("editPre")
    public ModelAndView editPre(int did,HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        try {
            Map<String, Object> map = detailsService.editPre(getEid(request), did);
            mav.addObject("allTypes",map.get("allTypes"));
            mav.addObject("allSubtypes",map.get("allSubtypes"));
            mav.addObject("details",map.get("details"));
            mav.setViewName(getMsg("details.edit.page"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mav;
    }

    @RequestMapping("edit")
    public ModelAndView edit(Details details,MultipartFile pic,HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        if (isAuth(25,request)){
            if (pic!=null&&pic.getSize()>0){
                if ("nophoto.png".equals(details.getPhoto())){
                    details.setPhoto(createSingleFileName(pic));
                }
            }
            details.setEmp(new Emp());
            details.getEmp().setEid(getEid(request));
            try {
                if (detailsService.edit(getEid(request),details)){
                    saveUpdateFile(pic,request,details.getPhoto());
                    setMsgAndUrl("vo.edit.success","details.list.action",mav);
                }else {
                    setMsgAndUrl("vo.edit.failure","details.list.action",mav);
                }
            } catch (Exception e) {
                e.printStackTrace();
                setMsgAndUrl("vo.edit.failure","details.list.action",mav);
            }
            mav.setViewName(getMsg("forward.page"));
        }else {
            mav.setViewName(getMsg("errors.page"));
        }
        return mav;
    }

    @RequestMapping("prebuy")
    public ModelAndView prebuy(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        if (isAuth(25,request)){
            try {
                mav.addObject("allDetails",detailsService.listPrebuy(getEid(request)));
            } catch (Exception e) {
                e.printStackTrace();
            }
            mav.setViewName(getMsg("details.list.page"));
        }else{
            mav.setViewName(getMsg("errors.page"));
        }
        return mav;
    }

    @RequestMapping("editAmount")
    public ModelAndView editAmount(String updateStr, String deleteStr, HttpServletRequest request, HttpServletResponse response){
        if (isAuth(25,request)){
            String[] updateResult = updateStr.split("\\|");
            Map<Integer,Integer> updateMap = new HashMap<Integer, Integer>();
            for (int x = 0; x < updateResult.length; x++) {
                String[] temp = updateResult[x].split(":");
                updateMap.put(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]));
            }
            Set<Integer> deleteIds = new HashSet<Integer>();
            if(!(deleteStr==null||"".equals(deleteStr))){
                String[] deleteResult = deleteStr.split("\\|");
                for (int x = 0; x < deleteResult.length; x++) {
                    deleteIds.add(Integer.parseInt(deleteResult[x]));
                }
            }
            try {
                Map<String,Object> map = detailsService.editAmount(getEid(request),updateMap,deleteIds);
                List<Details> allDetails = (List<Details>)map.get("allDetails");
                if (allDetails!=null){//有数据删除
                    Iterator<Details> iter = allDetails.iterator();
                    while (iter.hasNext()){
                        deleteFile(request,iter.next().getPhoto());
                    }
                }
                print(response,map.get("flag"));
            } catch (Exception e) {
                e.printStackTrace();
                print(response,false);
            }
        }else{
            print(response,false);
        }
        return null;
    }

    @RequestMapping("removeDetails")
    public ModelAndView removeDetails(String deleteStr, HttpServletRequest request, HttpServletResponse response){
        if(isAuth(25,request)){
            Set<Integer> deleteIds = new HashSet<Integer>();
            if(!(deleteStr==null||"".equals(deleteStr))){
                String[] deleteResult = deleteStr.split("\\|");
                for (int x = 0; x < deleteResult.length; x++) {
                    deleteIds.add(Integer.parseInt(deleteResult[x]));
                }
            }
            try {
                Map<String, Object> map = detailsService.removeDetailsBatch(getEid(request), deleteIds);
                List<Details> allDetails = (List<Details>)map.get("allDetails");
                if (allDetails!=null||allDetails.size()!=0){
                    Iterator<Details> iter = allDetails.iterator();
                    while(iter.hasNext()){
                        deleteFile(request,iter.next().getPhoto());
                    }
                }
                print(response,map.get("flag"));
            } catch (Exception e) {
                e.printStackTrace();
                print(response,false);
            }
        }else {
            print(response,false);
        }
        return null;
    }

    public String getSaveFileDiv() {
        return "/upload/res/";
    }

    public String getFlag() {
        return "待购办公用品";
    }
}
