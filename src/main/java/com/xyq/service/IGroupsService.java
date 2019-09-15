package com.xyq.service;

import com.xyq.vo.Groups;

import java.util.List;

/**
 * @Author xyq
 * @create 2019-09-15 13:46
 */
public interface IGroupsService {
    /**
     * 查看一个部门对应的所有权限数据
     * @param eid
     * @param did
     * @return
     * @throws Exception
     */
    public List<Groups> listByDept(int eid,int did) throws Exception;
}
