package com.xyq.dao.impl;

import com.xyq.dao.IEmpDao;
import com.xyq.dao.abs.AbstractDao;
import com.xyq.vo.Emp;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * @Author xyq
 * @create 2019-09-09 15:22
 */
@Component
public class EmpDaoImpl extends AbstractDao implements IEmpDao {
    public boolean findLogin(Emp vo) throws Exception {
        Emp emp = getSession().selectOne("EmpNS.findLogin",vo);
        if(emp!=null){
            vo.setName(emp.getName());
            vo.setPhoto(emp.getPhoto());
            vo.setAflag(emp.getAflag());
            vo.setDept(emp.getDept());
            return true;
        }
        return false;
    }

    public boolean doCreate(Emp vo) throws Exception {
        return getSession().insert("EmpNS.doCreate",vo)>0;
    }

    public boolean doUpdate(Emp vo) throws Exception {
        return false;
    }

    public boolean doRemoveBatch(Set<Integer> ids) throws Exception {
        return false;
    }

    public Emp findById(Integer id) throws Exception {
        return null;
    }

    public List<Emp> findAll() throws Exception {
        return null;
    }

    public List<Emp> findAllSplit(String column, String keyWord, Integer currentPage, Integer lineSize) throws Exception {
        return null;
    }

    public Integer getAllCount(String column, String keyWord) throws Exception {
        return null;
    }
}
