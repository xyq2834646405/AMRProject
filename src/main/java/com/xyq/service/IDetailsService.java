package com.xyq.service;

import com.xyq.vo.Details;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author xyq
 * @create 2019-09-16 15:15
 */
public interface IDetailsService {
    /**
     * 购买商品清单增加前的数据查询,需要校验
     * @param eid
     * @return 返回的内容包含如下内容:<br>
     *     <li>key=allTypes、value=ITypeDao.findAll()</li>
     * @throws Exception
     */
    public Map<String,Object> addPre(int eid) throws Exception;

    /**
     * 添加购买商品
     * @param vo
     * @param eid
     * @return
     * @throws Exception
     */
    public boolean add(Details vo,int eid) throws Exception;

    /**
     * 进行指定用户的所有待购商品的列表显示
     * @param eid
     * @return
     * @throws Exception
     */
    public List<Details> listPrebuy(int eid) throws Exception;

    /**
     * 实现数据购买数量的编辑处理操作
     * @param eid 当前的用户id,进行权限的验证
     * @param updateMap 商品修改的数量key=did,value=amount
     * @param deleteIds 所有购买数量为0的商品信息
     * @return 返回的内容包含如下内容:<br>
     *     <li>key=flag,描述成功或失败</li>
     *     <li>key=allDetails、value=IDetailsDao.findAllByPhoto</li>
     * @throws Exception
     */
    public Map<String,Object> editAmount(int eid, Map<Integer,Integer> updateMap, Set<Integer> deleteIds) throws Exception;

    /**
     * 待购商品中的删除商品操作
     * @param eid
     * @param deleteIds
     * @return 返回的内容包含如下内容:<br>
     *     <li>key=flag,描述成功或失败</li>
     *     <li>key=allDetails、value=IDetailsDao.findAllByPhoto</li>
     * @throws Exception
     */
    public Map<String,Object> removeDetailsBatch(int eid,Set<Integer> deleteIds)throws Exception;

    /**
     * 修改购物清单信息
     * @param eid
     * @param did
     * @return 返回的内容包含如下内容:<br>
     *     <li>key=allTypes、value=ITypeDao.findAll</li>
     *     <li>key=allSubtypes、value=ISubtypeDao.findAllByType</li>
     *     <li>key=details、value=IDetailsDao.findByIdAndPrebuy</li>
     * @throws Exception
     */
    public Map<String,Object> editPre(int eid,int did) throws Exception;

    /**
     * 实现数据的更新处理操作
     * @param eid
     * @param details
     * @return
     * @throws Exception
     */
    public boolean edit(int eid,Details details) throws Exception;

    /**
     * 实现已有办公用品的追加购买处理
     * @param eid 雇员编号
     * @param rid 办公用品编号
     * @return
     * @throws Exception
     */
    public boolean addAppend(int eid,int rid) throws Exception;
}
