package com.xyq.service;

import com.xyq.vo.Type;

import java.util.List;

/**
 * @Author xyq
 * @create 2019-09-16 10:47
 */
public interface ITypeService {
    /**
     * 商品的分类列表
     * @return
     * @throws Exception
     */
    public List<Type> list() throws Exception;

    /**
     * 修改商品分类信息
     * @param vo
     * @param eid
     * @return
     * @throws Exception
     */
    public boolean edit(Type vo,int eid) throws Exception;
}
