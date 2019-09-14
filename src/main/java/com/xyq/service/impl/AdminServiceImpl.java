package com.xyq.service.impl;

import com.xyq.dao.IEmpDao;
import com.xyq.dao.ILevelDao;
import com.xyq.service.IAdminService;
import com.xyq.service.abs.AbstractService;
import com.xyq.vo.Dept;
import com.xyq.vo.Emp;
import com.xyq.vo.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xyq
 * @create 2019-09-10 20:07
 */
@Service
public class AdminServiceImpl extends AbstractService implements IAdminService {
    @Autowired
    private ILevelDao levelDao;
    @Autowired
    private IEmpDao empDao;

    public Map<String, Object> addPre() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("allLevels",levelDao.findAll());
        return map;
    }

    public boolean add(Emp vo,int eid) throws Exception {
        if (!checkAuth(eid,2)){
            return false;
        }
        if(empDao.findById(vo.getEid())!=null){
            return false;
        }
        Level level = levelDao.findById(vo.getLevel().getLid());
        if(vo.getSalary()>level.getHisal()&&vo.getSalary()<level.getLosal()){
            return false;
        }
        vo.setAflag(2);
        vo.setDept(new Dept());
        vo.getDept().setDid(1);
        return empDao.doCreate(vo);
    }

    public boolean checkEid(int eid) throws Exception {
        return empDao.findById(eid)!=null;
    }

    public Map<String, Object> list(String column, String keyWord, int currentPage, int lineSize) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("allEmps",empDao.findAllAdmin(column,keyWord,currentPage,lineSize));
        map.put("EmpCount",empDao.getAllAdminCount(column,keyWord));
        return map;
    }
}
