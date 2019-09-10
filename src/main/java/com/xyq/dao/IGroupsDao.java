package com.xyq.dao;

import com.xyq.vo.Groups;

import java.util.List;

/**
 * @Author xyq
 * @create 2019-09-10 00:37
 */
public interface IGroupsDao extends IDao<Integer, Groups> {
    /**
     * 根据部门编号取得部门对应的所有权限组的数据,此时要使用子查询
     * @param did 部门编号
     * @return 所有的权限组
     * @throws Exception
     */
    public List<Groups> findAllByDept(Integer did) throws Exception;
}
