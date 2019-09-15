package com.xyq.dao;

import com.xyq.vo.Emp;

import java.util.List;

/**
 * @Author xyq
 * @create 2019-09-09 15:21
 */
public interface IEmpDao extends IDao<Integer, Emp> {
    /**
     * 登录之后取出用户名、图片等信息
     * @param vo
     * @return
     * @throws Exception
     */
    public boolean findLogin(Emp vo) throws Exception;

    /**
     * 查询所有管理员数据
     * @param column
     * @param keyWord
     * @param currentPage
     * @param lineSize
     * @return
     * @throws Exception
     */
    public List<Emp> findAllAdmin(String column,String keyWord,Integer currentPage,Integer lineSize) throws Exception;

    /**
     * 查询所有管理员数量
     * @param column
     * @param keyWord
     * @return
     * @throws Exception
     */
    public Integer getAllAdminCount(String column,String keyWord) throws Exception;

    /**
     * 查询所有雇员数据
     * @param column
     * @param keyWord
     * @param currentPage
     * @param lineSize
     * @return
     * @throws Exception
     */
    public List<Emp> findAllEmp(String column,String keyWord,Integer currentPage,Integer lineSize) throws Exception;

    /**
     * 查询所有雇员数量
     * @param column
     * @param keyWord
     * @return
     * @throws Exception
     */
    public Integer getAllEmpCount(String column,String keyWord) throws Exception;
}
