package com.xyq.service;

import com.xyq.vo.Take;

import java.util.Map;
import java.util.Set;

/**
 * @Author xyq
 * @create 2019-09-19 21:35
 */
public interface ITakeService {
    /**
     * 实现带领取用品的信息保存
     * @param eid
     * @param vo
     * @return
     * @throws Exception
     */
    public boolean add(int eid, Take vo) throws Exception;

    /**
     * 列出所有的待领取的数据信息
     * @param eid
     * @return 返回的内容包含如下数据<br>
     *     <li>key=allRess、value=IResDao.findAllByIds</li>
     *     <li>key=allTakes、value=Map<rid,Take> </li>
     * @throws Exception
     */
    public Map<String,Object> listUnGet(int eid) throws Exception;

    /**
     * 对待领取用品进行个数的编辑
     * @param eid 当前操作的雇员信息
     * @param map 包含有每一个tkid和数量的组合
     * @return
     * @throws Exception
     */
    public boolean edit(int eid,Map<Integer,Integer> map) throws Exception;

    /**
     * 实现待领取用品的删除处理
     * @param eid
     * @param ids
     * @return
     * @throws Exception
     */
    public boolean remove(int eid, Set<Integer> ids) throws Exception;

    /**
     * 提交用户的领取申请操作
     * @param eid
     * @return
     * @throws Exception
     */
    public boolean editSubmit(int eid) throws Exception;

    /**
     * 实现雇员个人领取记录列表
     * @param eid
     * @param currentPage
     * @param lineSize
     * @return
     * @throws Exception
     */
    public Map<String,Object> listByEmp(int eid,int currentPage,int lineSize) throws Exception;

    /**
     * 进行所有申请记录的列表显示
     * @param eid
     * @param column
     * @param keyWord
     * @param currentPage
     * @param lineSize
     * @return
     * @throws Exception
     */
    public Map<String,Object> list(int eid,String column,String keyWord,int currentPage,int lineSize) throws Exception;

    /**
     * 实现领取审核处理
     * @param eid
     * @param vo
     * @return
     * @throws Exception
     */
    public boolean editAudit(int eid,Take vo) throws Exception;

    /**
     * 用品的归还操作处理
     * @param eid
     * @param tkid
     * @return
     * @throws Exception
     */
    public boolean editRflag(int eid,int tkid) throws Exception;

    /**
     * 实现物品的归还处理
     * @param eid
     * @param tkid
     * @return
     * @throws Exception
     */
    public boolean editRdate(int eid,int tkid) throws Exception;
}
