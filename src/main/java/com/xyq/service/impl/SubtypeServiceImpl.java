package com.xyq.service.impl;

import com.xyq.dao.ISubtypeDao;
import com.xyq.service.ISubtypeService;
import com.xyq.service.abs.AbstractService;
import com.xyq.vo.Subtype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author xyq
 * @create 2019-09-16 12:27
 */
@Service
public class SubtypeServiceImpl extends AbstractService implements ISubtypeService {
    @Autowired
    private ISubtypeDao subtypeDao;

    public List<Subtype> list(int tid) throws Exception {
        return subtypeDao.findAllByType(tid);
    }

    public boolean edit(Subtype vo, int eid) throws Exception {
        if(!checkAuth(eid,10))
            return false;
        return subtypeDao.doUpdate(vo);
    }
}
