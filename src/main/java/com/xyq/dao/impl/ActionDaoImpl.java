package com.xyq.dao.impl;

import com.xyq.dao.IActionDao;
import com.xyq.dao.abs.AbstractDao;
import com.xyq.vo.Action;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * @Author xyq
 * @create 2019-09-10 00:39
 */
@Component
public class ActionDaoImpl extends AbstractDao implements IActionDao {
    public List<Action> findAllByGroups(Integer gid) throws Exception {
        return getSession().selectList("ActionNS.findAllByGroups",gid);
    }

    public boolean doCreate(Action vo) throws Exception {
        return false;
    }

    public boolean doUpdate(Action vo) throws Exception {
        return false;
    }

    public boolean doRemoveBatch(Set<Integer> ids) throws Exception {
        return false;
    }

    public Action findById(Integer id) throws Exception {
        return null;
    }

    public List<Action> findAll() throws Exception {
        return null;
    }

    public List<Action> findAllSplit(String column, String keyWord, Integer currentPage, Integer lineSize) throws Exception {
        return null;
    }

    public Integer getAllCount(String column, String keyWord) throws Exception {
        return null;
    }
}
