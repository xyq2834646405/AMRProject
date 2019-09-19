package com.xyq.dao;

import com.xyq.vo.Res;

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
}
