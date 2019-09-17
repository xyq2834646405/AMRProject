package com.xyq.dao;

import com.xyq.vo.Details;

import java.util.List;
import java.util.Set;

/**
 * @Author xyq
 * @create 2019-09-16 16:01
 */
public interface IDetailsDao extends IDao<Integer, Details> {
    /**
     * 查询指定用户所要购买的全部商品记录
     * @param eid
     * @return
     * @throws Exception
     */
    public List<Details> findAllPrebuy(Integer eid) throws Exception;

    /**
     * 实现数据的更新处理操作,主要是修改数量
     * @param vo
     * @return
     * @throws Exception
     */
    public boolean doUpdateAmount(Details vo) throws Exception;

    /**
     * 实现数据的批量删除操作
     * @param ids
     * @return
     * @throws Exception
     */
    public boolean doRemoveByAmount(Set<Integer> ids) throws Exception;

    /**
     * 取得所有的指定的编号的图片信息
     * @param ids
     * @return
     * @throws Exception
     */
    public List<Details> findAllByPhoto(Set<Integer> ids) throws Exception;

    /**
     * 查询要修改的待购商品数据
     * @return
     * @throws Exception
     */
    public Details findByIdAndPrebuy(Integer eid,Integer id) throws Exception;

    /**
     * 修改指定的商品信息
     * @param vo
     * @return
     * @throws Exception
     */
    public boolean doUpdatePrebuy(Details vo) throws Exception;
}
