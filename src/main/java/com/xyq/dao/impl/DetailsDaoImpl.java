package com.xyq.dao.impl;

import com.xyq.dao.IDetailsDao;
import com.xyq.dao.abs.AbstractDao;
import com.xyq.vo.Details;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public List<Details> findAllByPhoto(Set<Integer> ids) throws Exception {
        return getSession().selectList("DetailsNS.findAllByPhoto",ids.toArray());
    }

    public Details findByIdAndPrebuy(Integer eid,Integer id) throws Exception {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("did",id);
        map.put("eid",eid);
        return getSession().selectOne("DetailsNS.findByIdAndPrebuy",map);
    }

    public boolean doUpdatePrebuy(Details vo) throws Exception {
        return getSession().update("DetailsNS.doUpdatePrebuy",vo)>0;
    }

    public boolean doUpdateByPurchase(Integer pid, Integer eid) throws Exception {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("pid",pid);
        map.put("eid",eid);
        return getSession().update("DetailsNS.doUpdateByPurchase",map)>0;
    }

    public List<Details> findAllByPurchase(Integer pid) throws Exception {
        return getSession().selectList("DetailsNS.findAllByPurchase",pid);
    }

    public Details findByDetailsExists(Integer eid, Integer rid) throws Exception {
        Map<String,Integer> map = new HashMap<String, Integer>();
        map.put("eid",eid);
        map.put("rid",rid);
        return getSession().selectOne("DetailsNS.findByDetailsExists",map);
    }

    public boolean doUpdateAppendAmount(Integer did) throws Exception {
        return getSession().update("DetailsNS.doUpdateAppendAmount",did)>0;
    }
}
