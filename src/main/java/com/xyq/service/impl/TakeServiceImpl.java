package com.xyq.service.impl;

import com.xyq.dao.IResDao;
import com.xyq.dao.ITakeDao;
import com.xyq.service.ITakeService;
import com.xyq.service.abs.AbstractService;
import com.xyq.vo.Res;
import com.xyq.vo.Take;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author xyq
 * @create 2019-09-19 21:36
 */
@Service
public class TakeServiceImpl extends AbstractService implements ITakeService {
    @Autowired
    private ITakeDao takeDao;
    @Autowired
    private IResDao resDao;

    public boolean add(int eid, Take vo) throws Exception {
        if (!(checkAuth(eid,22,34,40))){
            return false;
        }
        Integer tkid = takeDao.findByResAndEmp(eid, vo.getRes().getRid());
        if(tkid==null||tkid.equals(0)){
            vo.setGeid(eid);
            vo.setAmount(1);
            return takeDao.doCreate(vo);
        }else{
            return takeDao.doUpdateAmount(tkid,1);
        }
    }

    public Map<String, Object> listUnGet(int eid) throws Exception {
        if (!(checkAuth(eid,22,34,40))){
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        List<Take> takeList = takeDao.findAllByEmpUnGet(eid);
        Map<Integer,Take> allTakes = new HashMap<Integer, Take>();
        Iterator<Take> iter = takeList.iterator();
        while(iter.hasNext()){
            Take take = iter.next();
            allTakes.put(take.getRes().getRid(),take);
        }
        map.put("allTakes",allTakes);
        if(allTakes.size()>0){
            map.put("allRess",resDao.findAllByRids(allTakes.keySet()));
        }
        return map;
    }

    public boolean edit(int eid, Map<Integer, Integer> map) throws Exception {
        if (!(checkAuth(eid,22,34,40))){
            return false;
        }
        if (map.size()==0){
            return false;
        }
        Set<Integer> tkids = new HashSet<Integer>();
        Iterator<Map.Entry<Integer, Integer>> iter = map.entrySet().iterator();
        while(iter.hasNext()){
            Map.Entry<Integer, Integer> me = iter.next();
            Integer tkid = me.getKey();
            Integer amount = me.getValue();
            if(amount>0){
                takeDao.doUpdateAmountByEmp(tkid,eid,amount);
            }else{
                tkids.add(tkid);
            }
        }
        if(tkids.size()>0){
            takeDao.doRemoveByEmp(tkids);
        }
        return true;
    }

    public boolean remove(int eid, Set<Integer> ids) throws Exception {
        if (!(checkAuth(eid,22,34,40))){
            return false;
        }
        if (ids.size()==0){
            return false;
        }
        return takeDao.doRemoveByEmp(ids);
    }

    public boolean editSubmit(int eid) throws Exception {
        if (!(checkAuth(eid,22,34,40))){
            return false;
        }
        List<Take> all = takeDao.findAllByEmpUnGet(eid);
        Iterator<Take> iter = all.iterator();
        while(iter.hasNext()){
            Take take = iter.next();
            take.setStatus(0);
            takeDao.doUpdateSubmit(take);
        }
        return true;
    }

    public Map<String, Object> listByEmp(int eid, int currentPage, int lineSize) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Take> allTakes = takeDao.findAllByEmp(eid, currentPage, lineSize);
        Iterator<Take> iterTake = allTakes.iterator();
        Set<Integer> rids = new HashSet<Integer>();
        while(iterTake.hasNext()){
            Take take = iterTake.next();
            rids.add(take.getRes().getRid());
        }
        List<Res> allRes = resDao.findAllByRids(rids);
        Iterator<Res> iterRes = allRes.iterator();
        Map<Integer,Res> resMap = new HashMap<Integer, Res>();
        while(iterRes.hasNext()){
            Res res = iterRes.next();
            resMap.put(res.getRid(),res);
        }
        map.put("allTakes",allTakes);
        map.put("resMap",resMap);
        map.put("takeCount",takeDao.getAllCountByEmp(eid));
        return map;
    }

    public Map<String, Object> list(int eid, String column, String keyWord, int currentPage, int lineSize) throws Exception {
        if (!super.checkAuth(eid, 28)) {
            return null ;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        List<Take> allTakes = takeDao.findAllSplit(column, "%"+keyWord+"%", currentPage, lineSize);
        Iterator<Take> iter = allTakes.iterator();
        Set<Integer> rids = new HashSet<Integer>();
        while (iter.hasNext()) {
            Take take = iter.next();
            rids.add(take.getRes().getRid());
        }
        List<Res> allRes = resDao.findAllByRids(rids);
        Iterator<Res> iterRes = allRes.iterator();
        Map<Integer, Res> resMap = new HashMap<Integer, Res>();
        while (iterRes.hasNext()) {
            Res res = iterRes.next();
            resMap.put(res.getRid(), res);
        }
        map.put("allTakes", allTakes);
        map.put("resMap", resMap);
        map.put("takeCount", this.takeDao.getAllCount(column, "%"+keyWord+"%"));
        return map;
    }

    public boolean editAudit(int eid, Take vo) throws Exception {
        if (!checkAuth(eid,28)){
            return false;
        }
        vo.setGdate(new Date());
        if(vo.getStatus().equals(1)){
            Take take = takeDao.findById(vo.getTkid());
            Res res = resDao.findById(take.getRes().getRid());
            if(res.getAmount()-take.getAmount()<0){
                return false;
            }
            if(takeDao.doUpdateStatus(vo)){
                return resDao.doUpdateAmount(res.getRid(),0-take.getAmount());
            }
        }else{
            takeDao.doUpdateStatus(vo);
        }
        return false;
    }

    public boolean editRflag(int eid, int tkid) throws Exception {
        if(!checkAuth(eid,22,34,40))
            return false;
        return takeDao.doUpdateStatus(tkid,3);
    }

    public boolean editRdate(int eid, int tkid) throws Exception {
        if(!checkAuth(eid,22,34,40))
            return false;
        Take take = takeDao.findById(tkid);
        Res res = resDao.findById(take.getRes().getRid());
        if(takeDao.doUpdateStatus(tkid,4,new Date())){
            return resDao.doUpdateAmount(res.getRid(),res.getAmount());
        }
        return false;
    }
}
