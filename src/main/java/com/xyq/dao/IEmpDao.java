package com.xyq.dao;

import com.xyq.vo.Emp;

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
}
