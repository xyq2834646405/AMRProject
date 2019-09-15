package com.xyq.service;

import com.xyq.vo.Emp;

import java.util.Map;

/**
 * @Author xyq
 * @create 2019-09-09 15:30
 */
public interface IEmpService {
    /**
     * 此处实现用户登录,必须保证密码经过了MD5加密处理,本操作要执行如下内容:<br>
     *     <li>执行IEmpDao.findLogin()方法进行登录判断</li>
     * @param vo
     * @return 登录成功返回true,反正返回false
     * @throws Exception
     */
    public boolean login(Emp vo) throws Exception;

    /**
     * 雇员增加前的查询处理操作
     * @return 返回的内容包括如下信息:<br>
     *     <li>key=allDepts、value=IDeptDao.findAllBySflag()</li>
     *     <li>key=allLevels、value=ILevelDao.findAll()</li>
     * @throws Exception
     */
    public Map<String,Object> addPre() throws Exception;

    /**
     * 雇员的增加操作
     * @param vo
     * @param eid
     * @return
     * @throws Exception
     */
    public boolean add(Emp vo, int eid) throws Exception;

    /**
     * 实现所有雇员数据的列表显示处理操作
     * @param column
     * @param keyWord
     * @param currentPage
     * @param lineSize
     * @return 返回集合包括如下内容:<br>
     *     <li>key=allEmps、value=IEmpDao.findAllEmp()</li>
     *     <li>key=EmpCount、value=IEmpDao.findAllEmpCount()</li>
     * @throws Exception
     */
    public Map<String,Object> list(int eid,String column,String keyWord,int currentPage,int lineSize) throws Exception;

    /**
     * 雇员修改前的查询操作
     * @param eid
     * @return 返回的内容包含如下信息:<br>
     *     <li>key=allDepts、value=IDeptDao.findAllBySflag()</li>
     *     <li>key=allLevels、value=ILevelDao.findAll()</li>
     *     <li>key=emp、value=IEmpDao.findById()</li>
     * @throws Exception
     */
    public Map<String,Object> editPre(int eid) throws Exception;

    /**
     * 修改雇员信息操作
     * @param vo
     * @param eid
     * @return
     * @throws Exception
     */
    public boolean edit(Emp vo,int eid) throws Exception;
}
