package com.xyq.dao.impl;

import com.xyq.dao.IGroupsDao;
import com.xyq.dao.abs.AbstractDao;
import com.xyq.vo.Groups;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * @Author xyq
 * @create 2019-09-10 00:40
 */
@Component
public class GroupsDaoImpl extends AbstractDao implements IGroupsDao {
    public List<Groups> findAllByDept(Integer did) throws Exception {
        return getSession().selectList("GroupsNS.findAllByDept",did);
    }

    public boolean doCreate(Groups vo) throws Exception {
        return false;
    }

    public boolean doUpdate(Groups vo) throws Exception {
        return false;
    }

    public boolean doRemoveBatch(Set<Integer> ids) throws Exception {
        return false;
    }

    public Groups findById(Integer id) throws Exception {
        return null;
    }

    public List<Groups> findAll() throws Exception {
        return null;
    }

    public List<Groups> findAllSplit(String column, String keyWord, Integer currentPage, Integer lineSize) throws Exception {
        return null;
    }

    public Integer getAllCount(String column, String keyWord) throws Exception {
        return null;
    }
}
