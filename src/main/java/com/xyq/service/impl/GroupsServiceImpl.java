package com.xyq.service.impl;

import com.xyq.dao.IGroupsDao;
import com.xyq.service.IGroupsService;
import com.xyq.service.abs.AbstractService;
import com.xyq.vo.Groups;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author xyq
 * @create 2019-09-15 13:48
 */
@Service
public class GroupsServiceImpl extends AbstractService implements IGroupsService {
    @Autowired
    private IGroupsDao groupsDao;

    public List<Groups> listByDept(int eid, int did) throws Exception {
        if (!checkAuth(eid,6)){
            return null;
        }
        return groupsDao.findAllByDept(did);
    }
}
