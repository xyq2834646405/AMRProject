package com.xyq.dao;

import com.xyq.vo.Subtype;

import java.util.List;

/**
 * @Author xyq
 * @create 2019-09-16 12:24
 */
public interface ISubtypeDao extends IDao<Integer, Subtype> {
    /**
     * 根据类型列出所有对应的类型数据
     * @param tid
     * @return
     * @throws Exception
     */
    public List<Subtype> findAllByType(Integer tid) throws Exception;
}
