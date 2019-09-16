package com.xyq.dao.impl;

import com.xyq.dao.ISubtypeDao;
import com.xyq.dao.abs.AbstractDao;
import com.xyq.vo.Subtype;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * @Author xyq
 * @create 2019-09-16 12:25
 */
@Component
public class SubtypeDaoImpl extends AbstractDao implements ISubtypeDao {
    public List<Subtype> findAllByType(Integer tid) throws Exception {
        return getSession().selectList("SubtypeNS.findAllByType",tid);
    }

    public boolean doCreate(Subtype vo) throws Exception {
        return false;
    }

    public boolean doUpdate(Subtype vo) throws Exception {
        return getSession().update("SubtypeNS.doUpdate",vo)>0;
    }

    public boolean doRemoveBatch(Set<Integer> ids) throws Exception {
        return false;
    }

    public Subtype findById(Integer id) throws Exception {
        return null;
    }

    public List<Subtype> findAll() throws Exception {
        return null;
    }

    public List<Subtype> findAllSplit(String column, String keyWord, Integer currentPage, Integer lineSize) throws Exception {
        return null;
    }

    public Integer getAllCount(String column, String keyWord) throws Exception {
        return null;
    }
}
