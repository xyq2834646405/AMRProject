package com.xyq.dao.impl;

import com.xyq.dao.IDeptDao;
import com.xyq.dao.abs.AbstractDao;
import com.xyq.vo.Dept;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * @Author xyq
 * @create 2019-09-15 11:20
 */
@Component
public class DeptDaoImpl extends AbstractDao implements IDeptDao {
    public boolean doCreate(Dept vo) throws Exception {
        return false;
    }

    public boolean doUpdate(Dept vo) throws Exception {
        return getSession().update("DeptNS.doUpdate",vo)>0;
    }

    public boolean doRemoveBatch(Set<Integer> ids) throws Exception {
        return false;
    }

    public Dept findById(Integer id) throws Exception {
        return null;
    }

    public List<Dept> findAll() throws Exception {
        return getSession().selectList("DeptNS.findAll");
    }

    public List<Dept> findAllSplit(String column, String keyWord, Integer currentPage, Integer lineSize) throws Exception {
        return null;
    }

    public Integer getAllCount(String column, String keyWord) throws Exception {
        return null;
    }

    public List<Dept> findAllBySflag(Integer sflag) throws Exception {
        return getSession().selectList("DeptNS.findAllBySflag",sflag);
    }
}
