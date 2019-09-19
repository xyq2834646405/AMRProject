package com.xyq.action;

import com.xyq.action.adapter.AbstractActionAdapter;
import com.xyq.service.IResService;
import com.xyq.util.SplitUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author xyq
 * @create 2019-09-19 13:45
 */
@Controller
@RequestMapping("/pages/res/*")
public class ResAction extends AbstractActionAdapter {
    @Autowired
    private IResService resService;

    @RequestMapping("list")
    public ModelAndView list(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        SplitUtil split = new SplitUtil(this);
        handleSplit(request,split);
        try {
            Map<String, Object> map = resService.list(split.getColumn(), "%"+split.getKeyWord()+"%", split.getCurrentPage(), split.getLineSize());
            mav.addObject("allRess",map.get("allRess"));
            split.setAttribute(request,map.get("resCount"),"res.list.action");
        } catch (Exception e) {
            e.printStackTrace();
        }
        mav.setViewName(getMsg("res.list.page"));
        return mav;
    }

    public String getDefaultColumn() {
        return "title";
    }
}
