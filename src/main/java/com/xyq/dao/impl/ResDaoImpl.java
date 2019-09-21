package com.xyq.dao.impl;

import com.xyq.dao.IResDao;
import com.xyq.dao.abs.AbstractDao;
import com.xyq.vo.Res;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author xyq
 * @create 2019-09-18 22:42
 */
@Component
public class ResDaoImpl extends AbstractDao implements IResDao {
    public boolean doCreate(Res vo) throws Exception {
        return getSession().insert("ResNS.doCreate",vo)>0;
    }

    public boolean doUpdate(Res vo) throws Exception {
        return false;
    }

    public boolean doRemoveBatch(Set<Integer> ids) throws Exception {
        return false;
    }

    public Res findById(Integer id) throws Exception {
        return getSession().selectOne("ResNS.findById",id);
    }

    public List<Res> findAll() throws Exception {
        return null;
    }

    public List<Res> findAllSplit(String column, String keyWord, Integer currentPage, Integer lineSize) throws Exception {
        return listHandle(column,keyWord,currentPage,lineSize,"ResNS.findAllSplit");
    }

    public Integer getAllCount(String column, String keyWord) throws Exception {
        return countHandle(column,keyWord,"ResNS.getAllCount");
    }

    public boolean doUpdateAmount(Integer rid, Integer amount) throws Exception {
        Map<String,Integer> map = new HashMap<String, Integer>();
        map.put("amount",amount);
        map.put("rid",rid);
        return getSession().update("ResNS.doUpdateAmount",map)>0;
    }

    public List<Res> findAllByRids(Set<Integer> ids) throws Exception {
        return getSession().selectList("ResNS.findAllByRids",ids.toArray());
    }
}
