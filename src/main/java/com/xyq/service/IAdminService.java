package com.xyq.service;

import com.xyq.vo.Emp;

import java.util.Map;

/**
 * @Author xyq
 * @create 2019-09-10 20:05
 */
public interface IAdminService {
    /**
     * 实现数据增加前的查询处理
     * @return 包含如下的数据:<br>
     *     <li>key=allLevels、value=ILevelDao.findAll</li>
     * @throws Exception
     */
    public Map<String,Object> addPre() throws Exception;

    /**
     * 管理员数据增加操纵
     * @param vo 管理员数据的vo对象,本质上就是emp类,增加管理员标记都为2
     * @return 增加成功返回true,否则返回false
     * @throws Exception
     */
    public boolean add(Emp vo) throws Exception;
}
