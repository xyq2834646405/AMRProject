package com.xyq.action;

import com.xyq.action.adapter.AbstractActionAdapter;
import com.xyq.service.ILevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author xyq
 * @create 2019-09-12 14:29
 */
@Controller
@RequestMapping("/pages/level/*")
public class LevelAction extends AbstractActionAdapter {
    @Autowired
    private ILevelService levelService;

    @RequestMapping("checkSalary")
    public ModelAndView checkSalary(int lid, double salary, HttpServletResponse response){
        try {
            print(response,levelService.checkSalary(salary,lid));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
