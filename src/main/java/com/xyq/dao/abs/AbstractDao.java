package com.xyq.dao.abs;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

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
}
