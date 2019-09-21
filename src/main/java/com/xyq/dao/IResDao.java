package com.xyq.dao;

import com.xyq.vo.Res;

import java.util.List;
import java.util.Set;

/**
 * @Author xyq
 * @create 2019-09-18 22:42
 */
public interface IResDao extends IDao<Integer, Res> {
    /**
     * 实现已有商品数量的更新处理操作
     * @param rid
     * @param amount
     * @return
     * @throws Exception
     */
    public boolean doUpdateAmount(Integer rid,Integer amount) throws Exception;

    /**
     * 根据指定的编号范围查询出所有的商品信息
     * @param ids
     * @return
     * @throws Exception
     */
    public List<Res> findAllByRids(Set<Integer> ids) throws Exception;
}
