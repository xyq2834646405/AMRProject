package com.xyq.dao.impl;

import com.xyq.dao.ITakeDao;
import com.xyq.dao.abs.AbstractDao;
import com.xyq.vo.Take;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @Author xyq
 * @create 2019-09-19 21:30
 */
@Component
public class TakeDaoImpl extends AbstractDao implements ITakeDao {
    public Integer findByResAndEmp(Integer eid, Integer rid) throws Exception {
        Map<String,Integer> map = new HashMap<String, Integer>();
        map.put("eid",eid);
        map.put("rid",rid);
        return getSession().selectOne("TakeNS.findByResAndEmp",map);
    }

    public boolean doUpdateAmount(Integer tkid, Integer amount) throws Exception {
        Map<String,Integer> map = new HashMap<String, Integer>();
        map.put("tkid",tkid);
        map.put("amount",amount);
        return getSession().update("TakeNS.doUpdateAmount",map)>0;
    }

    public List<Take> findAllByEmpUnGet(Integer eid) throws Exception {
        return getSession().selectList("TakeNS.findAllByEmpUnGet",eid);
    }

    public boolean doUpdateAmountByEmp(Integer tkid, Integer eid, Integer amount) throws Exception {
        Map<String,Integer> map = new HashMap<String, Integer>();
        map.put("tkid",tkid);
        map.put("geid",eid);
        map.put("amount",amount);
        return getSession().update("TakeNS.doUpdateAmountByEmp",map)>0;
    }

    public boolean doRemoveByEmp(Set<Integer> ids) throws Exception {
        return getSession().delete("TakeNS.doRemoveByEmp",ids.toArray())>0;
    }

    public boolean doUpdateSubmit(Take vo) throws Exception {
        return getSession().update("TakeNS.doUpdateSubmit",vo)>0;
    }

    public List<Take> findAllByEmp(Integer eid, Integer currentPage, Integer lineSize) throws Exception {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("geid",eid);
        map.put("start",(currentPage-1)*lineSize);
        map.put("lineSize",lineSize);
        return getSession().selectList("TakeNS.findAllByEmp",map);
    }

    public Integer getAllCountByEmp(Integer eid) throws Exception {
        return getSession().selectOne("TakeNS.getAllCountByEmp",eid);
    }

    public boolean doUpdateStatus(Take vo) throws Exception {
        return getSession().update("TakeNS.doUpdateStatus",vo)>0;
    }

    public boolean doUpdateStatus(Integer tkid, Integer status) throws Exception {
        Map<String,Integer> map = new HashMap<String, Integer>();
        map.put("tkid",tkid);
        map.put("status",status);
        return getSession().update("TakeNS.doUpdateStrutsReturn",map)>0;
    }

    public boolean doUpdateStatus(Integer tkid, Integer status, Date rdate) throws Exception {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("tkid",tkid);
        map.put("status",status);
        map.put("rdate",rdate);
        return getSession().update("TakeNS.doUpdateStrutsReturn",map)>0;
    }

    public boolean doCreate(Take vo) throws Exception {
        return getSession().insert("TakeNS.doCreate",vo)>0;
    }

    public boolean doUpdate(Take vo) throws Exception {
        return false;
    }

    public boolean doRemoveBatch(Set<Integer> ids) throws Exception {
        return false;
    }

    public Take findById(Integer id) throws Exception {
        return getSession().selectOne("TakeNS.findById",id);
    }

    public List<Take> findAll() throws Exception {
        return null;
    }

    public List<Take> findAllSplit(String column, String keyWord, Integer currentPage, Integer lineSize) throws Exception {
        return listHandle(column,keyWord,currentPage,lineSize,"TakeNS.findAllSplit");
    }

    public Integer getAllCount(String column, String keyWord) throws Exception {
        return countHandle(column,keyWord,"TakeNS.getAllCount");
    }
}
