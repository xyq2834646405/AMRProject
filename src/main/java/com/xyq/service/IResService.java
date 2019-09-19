package com.xyq.service;

import java.util.Map;

/**
 * @Author xyq
 * @create 2019-09-19 13:20
 */
public interface IResService {
    /**
     * 数据的分页显示处理
     * @param column
     * @param keyWord
     * @param currentPage
     * @param lineSize
     * @return 返回的内容包括如下:<br>
     *     <li>key=allRess、value=IResDao.findAllSplit</li>
     *     <li>key=resCount、value=IResDao.getAllCount</li>
     * @throws Exception
     */
    public Map<String,Object> list(String column,String keyWord,int currentPage,int lineSize) throws Exception;
}
