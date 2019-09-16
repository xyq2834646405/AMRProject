package com.xyq.dao.impl;

import com.xyq.dao.ITypeDao;
import com.xyq.dao.abs.AbstractDao;
import com.xyq.vo.Type;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * @Author xyq
 * @create 2019-09-16 10:45
 */
@Component
public class TypeDaoImpl extends AbstractDao implements ITypeDao {
    public boolean doCreate(Type vo) throws Exception {
        return false;
    }

    public boolean doUpdate(Type vo) throws Exception {
        return getSession().update("TypeNS.doUpdate",vo)>0;
    }

    public boolean doRemoveBatch(Set<Integer> ids) throws Exception {
        return false;
    }

    public Type findById(Integer id) throws Exception {
        return null;
    }

    public List<Type> findAll() throws Exception {
        return getSession().selectList("TypeNS.findAll");
    }

    public List<Type> findAllSplit(String column, String keyWord, Integer currentPage, Integer lineSize) throws Exception {
        return null;
    }

    public Integer getAllCount(String column, String keyWord) throws Exception {
        return null;
    }
}
