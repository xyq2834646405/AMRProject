package com.xyq.service;

import com.xyq.vo.Purchase;

import java.util.Map;

/**
 * @Author xyq
 * @create 2019-09-17 21:11
 */
public interface IPurchaseService {
    /**
     * 购入申请单创建
     * @param eid 负责处理的雇员编号
     * @param vo 申请单的信息
     * @return
     * @throws Exception
     */
    public boolean add(int eid, Purchase vo) throws Exception;

    /**
     * 实现指定雇员的所有申请单的数据查询
     * @param eid 雇员编号
     * @param currentPage
     * @param lineSize
     * @return 返回的内容包括如下结果:<br>
     *     <li>key=allPurchases、value=IPurchaseDao.findAllByEmp</li>
     *     <li>key=purchaseCount、value=IPurchaseDao.getAllCountByEmp</li>
     * @throws Exception
     */
    public Map<String,Object> listByEmp(int eid,int currentPage,int lineSize) throws Exception;

    /**
     * 查询一个购入申请单的详细内容
     * @param eid
     * @param pid
     * @return
     * @throws Exception
     */
    public Purchase getByEmp(int eid,int pid) throws Exception;

    /**
     * 列出全部的购入申请清单信息
     * @param eid
     * @param currentPage
     * @param lineSize
     * @return 返回的内容包括如下结果:<br>
     *     <li>key=allPurchases、value=IPurchaseDao.findAllSimpleSplit</li>
     *     <li>key=purchaseCount、value=IPurchaseDao.getAllCountSimple</li>
     * @throws Exception
     */
    public Map<String,Object> listSimple(int eid,int currentPage,int lineSize) throws Exception;

    /**
     * 查询一个订单的详细数据
     * @param eid
     * @param pid
     * @return
     * @throws Exception
     */
    public Purchase show(int eid,int pid) throws Exception;

    /**
     * 修改购入单状态
     * @param eid
     * @param pid
     * @param status
     * @return
     * @throws Exception
     */
    public boolean editStatus(int eid,int pid,int status) throws Exception;
}
