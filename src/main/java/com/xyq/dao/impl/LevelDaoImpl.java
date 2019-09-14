package com.xyq.dao.impl;

import com.xyq.dao.ILevelDao;
import com.xyq.dao.abs.AbstractDao;
import com.xyq.vo.Level;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * @Author xyq
 * @create 2019-09-10 20:04
 */
@Component
public class LevelDaoImpl extends AbstractDao implements ILevelDao {
    public boolean doCreate(Level vo) throws Exception {
        return false;
    }

    public boolean doUpdate(Level vo) throws Exception {
        return false;
    }

    public boolean doRemoveBatch(Set<Integer> ids) throws Exception {
        return false;
    }

    public Level findById(Integer id) throws Exception {
        return getSession().selectOne("LevelNS.findById",id);
    }

    public List<Level> findAll() throws Exception {
        return getSession().selectList("LevelNS.findAll");
    }

    public List<Level> findAllSplit(String column, String keyWord, Integer currentPage, Integer lineSize) throws Exception {
        return null;
    }

    public Integer getAllCount(String column, String keyWord) throws Exception {
        return null;
    }
}
