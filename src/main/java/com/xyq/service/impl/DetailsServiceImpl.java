package com.xyq.service.impl;

import com.xyq.dao.IDetailsDao;
import com.xyq.dao.IResDao;
import com.xyq.dao.ISubtypeDao;
import com.xyq.dao.ITypeDao;
import com.xyq.service.IDetailsService;
import com.xyq.service.abs.AbstractService;
import com.xyq.vo.Details;
import com.xyq.vo.Emp;
import com.xyq.vo.Res;
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
    @Autowired
    private ISubtypeDao subtypeDao;
    @Autowired
    private IResDao resDao;

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

    public Map<String,Object> editAmount(int eid, Map<Integer, Integer> updateMap, Set<Integer> deleteIds) throws Exception {
        Map<String,Object> map = new HashMap<String, Object>();
        boolean flag = true;
        if (!checkAuth(eid,25))
            flag = false;
        if (flag){
            if(!(updateMap.size()==0||updateMap==null)){
                Iterator<Map.Entry<Integer, Integer>> iterator = updateMap.entrySet().iterator();
                while(iterator.hasNext()){
                    Map.Entry<Integer, Integer> me = iterator.next();
                    Details details = new Details();
                    details.setDid(me.getKey());
                    details.setAmount(me.getValue());
                    details.setEmp(new Emp());
                    details.getEmp().setEid(eid);
                    if(!detailsDao.doUpdateAmount(details))
                        flag=false;
                }
            }
            if(!(deleteIds.size()==0||deleteIds==null)){
                List<Details> allDetails = detailsDao.findAllByPhoto(deleteIds);
                Iterator<Details> iter = allDetails.iterator();
                while(iter.hasNext()){
                    Details details = iter.next();
                    if (!details.getEmp().getEid().equals(eid)){//要删除的不是自己
                        flag = false;
                        break;
                    }
                }
                if (flag){
                    if (deleteIds.size()>0){
                        flag = detailsDao.doRemoveByAmount(deleteIds);
                        if (flag){
                            map.put("allDetails",allDetails);
                        }
                    }
                }
            }
        }
        map.put("flag",flag);
        return map;
    }

    public Map<String,Object> removeDetailsBatch(int eid, Set<Integer> deleteIds) throws Exception {
        Map<String,Object> map = new HashMap<String, Object>();
        boolean flag = true;
        if (!checkAuth(eid,25))
            flag = false;
        if (deleteIds.size()==0)
            flag = false;
        if (flag){
            List<Details> allDetails = detailsDao.findAllByPhoto(deleteIds);
            Iterator<Details> iter = allDetails.iterator();
            while (iter.hasNext()){
                Details details = iter.next();
                if(!details.getEmp().getEid().equals(eid)){
                    flag = false;
                    break;
                }
            }
            if(flag){
                flag = detailsDao.doRemoveByAmount(deleteIds);
                if (flag){
                    map.put("allDetails",allDetails);
                }
            }
        }
        map.put("flag",flag);
        return map;
    }

    public Map<String, Object> editPre(int eid, int did) throws Exception {
        if (!checkAuth(eid,25))
            return null;
        Details details = detailsDao.findByIdAndPrebuy(eid, did);
        if (details==null)
            return null;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("allTypes",typeDao.findAll());
        map.put("allSubtypes",subtypeDao.findAllByType(details.getType().getTid()));
        map.put("details",details);
        return map;
    }

    public boolean edit(int eid, Details details) throws Exception {
        if (!checkAuth(eid,25))
            return false;
        return detailsDao.doUpdatePrebuy(details);
    }

    public boolean addAppend(int eid, int rid) throws Exception {
        if(!checkAuth(eid,25))
            return false;
        //1、判断此时数据是否存在
        Details details = detailsDao.findByDetailsExists(eid, rid);
        if (details==null){//没有数据
            //2、需要取得编号的办公用品追加
            Res res = resDao.findById(rid);
            if(res!=null){//现在已经有相对应的商品信息
                Details vo = new Details();
                vo.setRes(res);
                vo.setType(res.getType());
                vo.setSubtype(res.getSubtype());
                vo.setTitle(res.getTitle());
                vo.setEmp(new Emp());
                vo.getEmp().setEid(eid);
                vo.setPrice(res.getPrice());
                vo.setAmount(1);
                vo.setPhoto(res.getPhoto());
                vo.setRflag(res.getRflag());
                return detailsDao.doCreate(vo);
            }
        }else{
            return detailsDao.doUpdateAppendAmount(details.getDid());
        }
        return false;
    }
}
