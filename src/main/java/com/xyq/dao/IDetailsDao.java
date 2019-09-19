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

    /**
     * 根据清单的编号更新所有的待购入商品信息的清单
     * @param pid
     * @param eid
     * @return
     * @throws Exception
     */
    public boolean doUpdateByPurchase(Integer pid,Integer eid) throws Exception;

    /**
     * 根据一个申请单查询所有的购买详情数据
     * @param pid
     * @return
     * @throws Exception
     */
    public List<Details> findAllByPurchase(Integer pid) throws Exception;

    /**
     * 根据指定的雇员编号与追加用品编号判断改用品是否已经被追加
     * @param eid
     * @param rid
     * @return
     * @throws Exception
     */
    public Details findByDetailsExists(Integer eid,Integer rid) throws Exception;

    /**
     * 进行数量的增一处理
     * @param did
     * @return
     * @throws Exception
     */
    public boolean doUpdateAppendAmount(Integer did) throws Exception;
}
