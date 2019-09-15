package com.xyq.service.impl;

import com.xyq.dao.*;
import com.xyq.service.IEmpService;
import com.xyq.service.abs.AbstractService;
import com.xyq.vo.Emp;
import com.xyq.vo.Groups;
import com.xyq.vo.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Author xyq
 * @create 2019-09-09 15:32
 */
@Service
public class EmpServiceImpl extends AbstractService implements IEmpService {
    @Autowired
    private IEmpDao empDao;
    @Autowired
    private IGroupsDao groupsDao;
    @Autowired
    private IActionDao actionDao;
    @Autowired
    private ILevelDao levelDao;
    @Autowired
    private IDeptDao deptDao;

    public boolean login(Emp vo) throws Exception {
        if(empDao.findLogin(vo)){
            List<Groups> allGroups = groupsDao.findAllByDept(vo.getDept().getDid());
            Iterator<Groups> iter = allGroups.iterator();
            while(iter.hasNext()){
                Groups groups = iter.next();
                groups.setAllActions(actionDao.findAllByGroups(groups.getGid()));
            }
            vo.getDept().setAllGroups(allGroups);
            return true;
        }
        return false;
    }

    public Map<String, Object> addPre() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("allLevels",levelDao.findAll());
        map.put("allDepts",deptDao.findAllBySflag(0));
        return map;
    }

    public boolean add(Emp vo, int eid) throws Exception {
        if (!checkAuth(eid,12))
            return false;
        if(vo.getDept().getDid().equals(1))
            return false;
        if(empDao.findById(vo.getEid())!=null)
            return false;
        Level level = levelDao.findById(vo.getLevel().getLid());
        if(vo.getSalary()>level.getHisal()||vo.getSalary()<level.getLosal())
            return false;
        vo.setHeid(eid);
        vo.setAflag(0);
        return empDao.doCreate(vo);
    }

    public Map<String, Object> list(int eid, String column, String keyWord, int currentPage, int lineSize) throws Exception {
        if(!checkAuth(eid,13))
            return null;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("allEmps",empDao.findAllEmp(column,keyWord,currentPage,lineSize));
        map.put("EmpCount",empDao.getAllEmpCount(column,keyWord));
        return map;
    }

    public Map<String, Object> editPre(int eid) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("allDepts",deptDao.findAllBySflag(0));
        map.put("allLevels",levelDao.findAll());
        map.put("emp",empDao.findById(eid));
        return map;
    }

    public boolean edit(Emp vo, int eid) throws Exception {
        if(!checkAuth(eid,15))
            return false;
        if(vo.getDept().getDid().equals(1))
            return false;
        Level level = levelDao.findById(vo.getLevel().getLid());
        if(vo.getSalary()>level.getHisal()||vo.getSalary()<level.getLosal())
            return false;
        return empDao.doUpdate(vo);
    }

}
