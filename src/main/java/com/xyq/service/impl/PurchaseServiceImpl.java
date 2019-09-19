package com.xyq.service.impl;

import com.xyq.dao.IDetailsDao;
import com.xyq.dao.IEmpDao;
import com.xyq.dao.IPurchaseDao;
import com.xyq.dao.IResDao;
import com.xyq.service.IPurchaseService;
import com.xyq.service.abs.AbstractService;
import com.xyq.vo.Details;
import com.xyq.vo.Emp;
import com.xyq.vo.Purchase;
import com.xyq.vo.Res;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author xyq
 * @create 2019-09-17 21:13
 */
@Service
public class PurchaseServiceImpl extends AbstractService implements IPurchaseService {
    @Autowired
    private IPurchaseDao purchaseDao;
    @Autowired
    private IDetailsDao detailsDao;
    @Autowired
    private IEmpDao empDao;
    @Autowired
    private IResDao resDao;

    public boolean add(int eid, Purchase vo) throws Exception {
        if (!checkAuth(eid,30)){
            return false;
        }
        //1、取出所有当前用户所保存的待购入商品信息
        List<Details> allDetails = detailsDao.findAllPrebuy(eid);
        if (allDetails==null||allDetails.size()==0){
            return false;
        }
        //2、如果存在有待购数据,则计算总额
        double sum = 0.0;
        Iterator<Details> iter = allDetails.iterator();
        while (iter.hasNext()){
            Details details = iter.next();
            sum+=(details.getPrice()*details.getAmount());
        }
        if(sum<0.0){
            return false;
        }
        //3、创建清单的完整数据
        vo.setEmp(new Emp());
        vo.getEmp().setEid(eid);//设置申请人的雇员编号
        vo.setPubdate(new Date());//当前日期为申请日期
        vo.setStatus(0);//待审核状态
        vo.setTotal(sum);
        //4、保存清单数据
        if (purchaseDao.doCreate(vo)){
            return detailsDao.doUpdateByPurchase(vo.getPid(),eid);
        }
        return false;
    }

    public Map<String, Object> listByEmp(int eid, int currentPage, int lineSize) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("allPurchases",purchaseDao.findAllByEmp(eid,currentPage,lineSize));
        map.put("purchaseCount",purchaseDao.getAllCountByEmp(eid));
        return map;
    }

    public Purchase getByEmp(int eid, int pid) throws Exception {
        if(!checkAuth(eid,27))
            return null;
        Purchase purchase = purchaseDao.findByIdAndEmp(pid, eid);
        if(purchase!=null){
            purchase.setAllDetails(detailsDao.findAllByPurchase(pid));
            purchase.setEmp(empDao.findById(purchase.getEmp().getEid()));
        }
        return purchase;
    }

    public Map<String, Object> listSimple(int eid, int currentPage, int lineSize) throws Exception {
        if (!checkAuth(eid,41))
            return null;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("allPurchases",purchaseDao.findAllSimpleSplit(currentPage,lineSize));
        map.put("purchaseCount",purchaseDao.getAllCountSimple());
        return map;
    }

    public Purchase show(int eid, int pid) throws Exception {
        if(!checkAuth(eid,41))
            return null;
        Purchase purchase = purchaseDao.findById(pid);
        if(purchase!=null){
            purchase.setAllDetails(detailsDao.findAllByPurchase(pid));
            purchase.setEmp(empDao.findById(purchase.getEmp().getEid()));
        }
        return purchase;
    }

    public boolean editStatus(int eid, int pid, int status) throws Exception {
        if (!checkAuth(eid,42,4)){
            return false;
        }
        if(purchaseDao.doUpdateStatus(pid,status,eid)){
            if (status==1){
                List<Details> allDetails = detailsDao.findAllByPurchase(pid);
                Iterator<Details> iter = allDetails.iterator();
                while(iter.hasNext()){
                    Details details = iter.next();
                    if (details.getRes()==null || details.getRes().getRid()==null){
                        Res res = new Res();
                        res.setType(details.getType());
                        res.setSubtype(details.getSubtype());
                        res.setTitle(details.getTitle());
                        res.setPrice(details.getPrice());
                        res.setIndate(new Date());
                        res.setPhoto(details.getPhoto());
                        res.setRflag(details.getRflag());
                        res.setAmount(details.getAmount());
                        System.out.println(res);
                        resDao.doCreate(res);
                    }else{
                        resDao.doUpdateAmount(details.getRes().getRid(),details.getAmount());
                    }
                }
            }
        }
        return true;
    }
}
