package com.xyq.service.abs;

import com.xyq.dao.IActionDao;
import com.xyq.dao.IEmpDao;
import com.xyq.vo.Emp;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author xyq
 * @create 2019-09-09 14:03
 */
public abstract class AbstractService {
    @Autowired
    private IEmpDao empDao;
    @Autowired
    private IActionDao actionDao;

    /**
     * 检测当前的雇员编号是否具备有制定的权限处理
     * @param eid 雇员编号
     * @param actid 权限id
     * @return 如果具备有此权限则返回true,否则返回false
     * @throws Exception
     */
    public boolean checkAuth(int eid,int ... actid) throws Exception{
        Emp emp = empDao.findById(eid);
        if(emp.getAflag().equals(1)||emp.getAflag().equals(2)){
            return true;
        }
        for (int x:actid) {
            if(actionDao.findByIdAndDept(emp.getDept().getDid(),x)!=null){
                return true;
            }
        }
        return false;
    }

    /**
     * 检测当前的雇员编号是否具备此权限和级别
     * @param eid
     * @param actid
     * @param lid
     * @return
     * @throws Exception
     */
    public boolean checkAuth(int eid,int actid,int lid) throws Exception{
        Emp emp = empDao.findById(eid);
        if(emp.getAflag().equals(1)||emp.getAflag().equals(2)){
            return true;
        }
        if (emp.getLevel().getLid().equals(lid)){
            return actionDao.findByIdAndDept(emp.getDept().getDid(),actid)!=null;
        }
        return false;
    }
}
