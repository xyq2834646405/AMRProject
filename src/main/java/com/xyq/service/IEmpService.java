package com.xyq.service;

import com.xyq.vo.Emp;

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
}
