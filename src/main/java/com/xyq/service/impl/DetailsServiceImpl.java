package com.xyq.service.impl;

import com.xyq.dao.IDetailsDao;
import com.xyq.dao.ITypeDao;
import com.xyq.service.IDetailsService;
import com.xyq.service.abs.AbstractService;
import com.xyq.vo.Details;
import com.xyq.vo.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author xyq
 * @create 2019-09-16 15:17
 */
@Service
public class DetailsServiceImpl extends AbstractService implements IDetailsService {
    @Autowired
    private ITypeDao typeDao ;
    @Autowired
    private IDetailsDao detailsDao;

    public Map<String, Object> addPre(int eid) throws Exception {
        if(!checkAuth(eid,25)){
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("allTypes",typeDao.findAll());
        return map;
    }

    public boolean add(Details vo, int eid) throws Exception {
        if(!checkAuth(eid,25))
            return false;
        vo.setEmp(new Emp());
        vo.getEmp().setEid(eid);
        vo.setAmount(1);
        return detailsDao.doCreate(vo);
    }

    public List<Details> listPrebuy(int eid) throws Exception {
        if(!checkAuth(eid,25))
            return null;
        return detailsDao.findAllPrebuy(eid);
    }

    public boolean editAmount(int eid, Map<Integer, Integer> updateMap, Set<Integer> deleteIds) throws Exception {
        if (!checkAuth(eid,25))
            return false;
        boolean flag = true;
        Iterator<Map.Entry<Integer, Integer>> iterator = updateMap.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<Integer, Integer> map = iterator.next();
            Details details = new Details();
            details.setDid(map.getKey());
            details.setAmount(map.getValue());
            details.setEmp(new Emp());
            details.getEmp().setEid(eid);
            if(!detailsDao.doUpdateAmount(details))
                flag=false;
        }
        if (deleteIds.size()>0)
            flag = detailsDao.doRemoveByAmount(deleteIds);
        return flag;
    }

    public boolean removeDetailsBatch(int eid, Set<Integer> deleteIds) throws Exception {
        if (!checkAuth(eid,25))
            return false;
        if (deleteIds.size()==0)
            return false;
        return detailsDao.doRemoveByAmount(deleteIds);
    }
}
