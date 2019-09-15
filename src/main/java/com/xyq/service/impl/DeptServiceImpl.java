package com.xyq.service.impl;

import com.xyq.dao.IDeptDao;
import com.xyq.service.IDeptService;
import com.xyq.service.abs.AbstractService;
import com.xyq.vo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author xyq
 * @create 2019-09-15 11:22
 */
@Service
public class DeptServiceImpl extends AbstractService implements IDeptService {
    @Autowired
    private IDeptDao deptDao;

    public List<Dept> list(int eid) throws Exception {
        if (!checkAuth(eid,4)){
            return null;
        }
        return deptDao.findAll();
    }

    public boolean edit(int eid, Dept vo) throws Exception {
        if (!checkAuth(eid,7)){
            return false;
        }
        return deptDao.doUpdate(vo);
    }
}
