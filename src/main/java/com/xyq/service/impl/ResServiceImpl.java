package com.xyq.service.impl;

import com.xyq.dao.IResDao;
import com.xyq.service.IResService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xyq
 * @create 2019-09-19 13:21
 */
@Service
public class ResServiceImpl implements IResService {
    @Autowired
    private IResDao resDao;

    public Map<String, Object> list(String column, String keyWord, int currentPage, int lineSize) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("allRess",resDao.findAllSplit(column,keyWord,currentPage,lineSize));
        map.put("resCount",resDao.getAllCount(column,keyWord));
        return map;
    }
}
