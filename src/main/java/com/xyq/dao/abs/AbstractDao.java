package com.xyq.dao.abs;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author xyq
 * @create 2019-09-09 13:56
 */
public abstract class AbstractDao {
    @Autowired
    private SqlSessionFactory factory;

    public SqlSessionFactory getFactory(){
        return factory;
    }

    public SqlSession getSession(){
        return factory.openSession();
    }

    /**
     * 分页列表数据处理
     * @param column
     * @param keyWord
     * @param currentPage
     * @param lineSize
     * @param namespace
     * @param <E>
     * @return
     * @throws Exception
     */
    public <E> List<E> listHandle(String column,String keyWord,Integer currentPage,Integer lineSize,String namespace) throws Exception{
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("column",column);
        if(keyWord!=null){
            map.put("keyWord",keyWord);
        }
        map.put("start",(currentPage-1)*lineSize);
        map.put("lineSize",lineSize);
        return getSession().selectList(namespace,map);
    }

    /**
     * 分页列表数量处理
     * @param column
     * @param keyWord
     * @param namespace
     * @return
     * @throws Exception
     */
    public Integer countHandle(String column,String keyWord,String namespace) throws Exception{
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("column",column);
        if(keyWord!=null){
            map.put("keyWord",keyWord);
        }
        return getSession().selectOne(namespace,map);
    }


}
