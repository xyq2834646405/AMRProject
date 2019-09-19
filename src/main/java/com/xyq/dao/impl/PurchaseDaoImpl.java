package com.xyq.dao.impl;

import com.xyq.dao.IPurchaseDao;
import com.xyq.dao.abs.AbstractDao;
import com.xyq.vo.Purchase;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author xyq
 * @create 2019-09-17 21:08
 */
@Component
public class PurchaseDaoImpl extends AbstractDao implements IPurchaseDao {
    public boolean doCreate(Purchase vo) throws Exception {
        return getSession().insert("PurchaseNS.doCreate",vo)>0;
    }

    public boolean doUpdate(Purchase vo) throws Exception {
        return false;
    }

    public boolean doRemoveBatch(Set<Integer> ids) throws Exception {
        return false;
    }

    public Purchase findById(Integer id) throws Exception {
        return getSession().selectOne("PurchaseNS.findById",id);
    }

    public List<Purchase> findAll() throws Exception {
        return null;
    }

    public List<Purchase> findAllSplit(String column, String keyWord, Integer currentPage, Integer lineSize) throws Exception {
        return null;
    }

    public Integer getAllCount(String column, String keyWord) throws Exception {
        return null;
    }

    public List<Purchase> findAllByEmp(Integer eid, Integer currentPage, Integer lineSize) throws Exception {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("eid",eid);
        map.put("start",(currentPage-1)*lineSize);
        map.put("lineSize",lineSize);
        return getSession().selectList("PurchaseNS.findAllByEmp",map);
    }

    public Integer getAllCountByEmp(Integer eid) throws Exception {
        return getSession().selectOne("PurchaseNS.getAllCountByEmp",eid);
    }

    public Purchase findByIdAndEmp(Integer pid, Integer eid) throws Exception {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("eid",eid);
        map.put("pid",pid);
        return getSession().selectOne("PurchaseNS.findByIdAndEmp",map);
    }

    public List<Purchase> findAllSimpleSplit(Integer currentPage, Integer lineSize) throws Exception {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("start",(currentPage-1)*lineSize);
        map.put("lineSize",lineSize);
        return getSession().selectList("PurchaseNS.findAllSimpleSplit",map);
    }

    public Integer getAllCountSimple() throws Exception {
        return getSession().selectOne("PurchaseNS.getAllCountSimple");
    }

    public boolean doUpdateStatus(Integer pid, Integer status, Integer eid) throws Exception {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("pid",pid);
        map.put("status",status);
        map.put("meid",eid);
        return getSession().update("PurchaseNS.doUpdateStatus",map)>0;
    }
}
