package com.xyq.dao;

import com.xyq.vo.Action;

import java.util.List;

/**
 * @Author xyq
 * @create 2019-09-10 00:38
 */
public interface IActionDao extends IDao<Integer, Action> {
    /**
     * 取得制定权限组对应的所有权限数据
     * @param gid 权限组编号
     * @return 该权限组对应的所有权限信息
     * @throws Exception
     */
    public List<Action> findAllByGroups(Integer gid) throws Exception;
}
