package com.xyq.service.impl;

import com.xyq.dao.IActionDao;
import com.xyq.service.IActionService;
import com.xyq.service.abs.AbstractService;
import com.xyq.vo.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author xyq
 * @create 2019-09-15 14:13
 */
@Service
public class ActionServiceImpl extends AbstractService implements IActionService {
    @Autowired
    private IActionDao actionDao;

    public List<Action> listByGroups(int eid, int gid) throws Exception {
        if(!checkAuth(eid,6)){
            return null;
        }
        return actionDao.findAllByGroups(gid);
    }
}
