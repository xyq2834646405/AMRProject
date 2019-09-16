package com.xyq.dao.impl;

import com.xyq.dao.IDetailsDao;
import com.xyq.dao.abs.AbstractDao;
import com.xyq.vo.Details;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * @Author xyq
 * @create 2019-09-16 16:02
 */
@Component
public class DetailsDaoImpl extends AbstractDao implements IDetailsDao {
    public boolean doCreate(Details vo) throws Exception {
        return getSession().insert("DetailsNS.doCreate",vo)>0;
    }

    public boolean doUpdate(Details vo) throws Exception {
        return false;
    }

    public boolean doRemoveBatch(Set<Integer> ids) throws Exception {
        return false;
    }

    public Details findById(Integer id) throws Exception {
        return null;
    }

    public List<Details> findAll() throws Exception {
        return null;
    }

    public List<Details> findAllSplit(String column, String keyWord, Integer currentPage, Integer lineSize) throws Exception {
        return null;
    }

    public Integer getAllCount(String column, String keyWord) throws Exception {
        return null;
    }

    public List<Details> findAllPrebuy(Integer eid) throws Exception {
        return getSession().selectList("DetailsNS.findAllPrebuy",eid);
    }

    public boolean doUpdateAmount(Details vo) throws Exception {
        return getSession().update("DetailsNS.doUpdateAmount",vo)>0;
    }

    public boolean doRemoveByAmount(Set<Integer> ids) throws Exception {
        return getSession().delete("DetailsNS.doRemoveBatchByAmount",ids.toArray())>0;
    }
}
