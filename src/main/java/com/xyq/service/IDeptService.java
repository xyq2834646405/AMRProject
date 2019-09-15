package com.xyq.service;

import com.xyq.vo.Dept;

import java.util.List;

/**
 * @Author xyq
 * @create 2019-09-15 11:21
 */
public interface IDeptService {
    /**
     * 查询用户是否有权限后查询所有的部门信息
     * @param eid
     * @return
     * @throws Exception
     */
    public List<Dept> list(int eid) throws Exception;

    /**
     * 查询用户是否有权限后修改部门名称
     * @param eid
     * @param vo
     * @return
     * @throws Exception
     */
    public boolean edit(int eid,Dept vo) throws Exception;
}
