package com.xyq.service.impl;

import com.xyq.dao.IActionDao;
import com.xyq.dao.IEmpDao;
import com.xyq.dao.IGroupsDao;
import com.xyq.service.IEmpService;
import com.xyq.service.abs.AbstractService;
import com.xyq.vo.Emp;
import com.xyq.vo.Groups;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

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
}
