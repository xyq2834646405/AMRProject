package com.xyq.dao;

import com.xyq.vo.Dept;

import java.util.List;

/**
 * @Author xyq
 * @create 2019-09-15 11:20
 */
public interface IDeptDao extends IDao<Integer, Dept> {
    /**
     * 根据sflag的内容查询所有的部门数据
     * @param sflag
     * @return
     * @throws Exception
     */
    public List<Dept> findAllBySflag(Integer sflag) throws Exception;
}
