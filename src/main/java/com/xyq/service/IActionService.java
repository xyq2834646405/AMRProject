package com.xyq.service;

import com.xyq.vo.Action;

import java.util.List;

/**
 * @Author xyq
 * @create 2019-09-15 14:12
 */
public interface IActionService {
    /**
     * 根据权限组列出对应的所有权限数据
     * @param eid
     * @param gid
     * @return
     * @throws Exception
     */
    public List<Action> listByGroups(int eid,int gid) throws Exception;
}
