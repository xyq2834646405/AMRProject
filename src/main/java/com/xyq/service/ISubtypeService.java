package com.xyq.service;

import com.xyq.vo.Subtype;

import java.util.List;

/**
 * @Author xyq
 * @create 2019-09-16 12:26
 */
public interface ISubtypeService {
    /**
     * 分类的所有分类列表
     * @param tid
     * @return
     * @throws Exception
     */
    public List<Subtype> list(int tid) throws Exception;

    /**
     * 子分类信息修改
     * @param vo
     * @param eid
     * @return
     * @throws Exception
     */
    public boolean edit(Subtype vo,int eid) throws Exception;
}
