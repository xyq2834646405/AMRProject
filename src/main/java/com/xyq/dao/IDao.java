package com.xyq.dao;

import java.util.List;
import java.util.Set;

/**
 * @Author xyq
 * @create 2019-09-09 13:58
 */
public interface IDao<K,V> {
    /**
     * 增加操作
     * @param vo
     * @return
     * @throws Exception
     */
    public boolean doCreate(V vo) throws Exception;

    /**
     * 修改操作
     * @param vo
     * @return
     * @throws Exception
     */
    public boolean doUpdate(V vo) throws Exception;

    /**
     * 批量删除
     * @param ids
     * @return
     * @throws Exception
     */
    public boolean doRemoveBatch(Set<K> ids) throws Exception;

    /**
     * 查询一条
     * @param id
     * @return
     * @throws Exception
     */
    public V findById(K id) throws Exception;

    /**
     * 查询全部
     * @return
     * @throws Exception
     */
    public List<V> findAll() throws Exception;

    /**
     * 分页查询
     * @param column
     * @param keyWord
     * @param currentPage
     * @param lineSize
     * @return
     * @throws Exception
     */
    public List<V> findAllSplit(String column,String keyWord,Integer currentPage,Integer lineSize) throws Exception;

    /**
     * 分页数量
     * @param column
     * @param keyWord
     * @return
     * @throws Exception
     */
    public Integer getAllCount(String column,String keyWord) throws Exception;
}
