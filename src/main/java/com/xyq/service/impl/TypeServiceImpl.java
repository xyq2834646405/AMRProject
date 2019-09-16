package com.xyq.service.impl;

import com.xyq.dao.ITypeDao;
import com.xyq.service.ITypeService;
import com.xyq.service.abs.AbstractService;
import com.xyq.vo.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author xyq
 * @create 2019-09-16 10:47
 */
@Service
public class TypeServiceImpl extends AbstractService implements ITypeService {
    @Autowired
    ITypeDao typeDao;

    public List<Type> list() throws Exception {
        return typeDao.findAll();
    }

    public boolean edit(Type vo, int eid) throws Exception {
        if(!checkAuth(eid,9))
            return false;
        return typeDao.doUpdate(vo);
    }
}
