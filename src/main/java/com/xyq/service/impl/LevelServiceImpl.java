package com.xyq.service.impl;

import com.xyq.dao.ILevelDao;
import com.xyq.service.ILevelService;
import com.xyq.vo.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author xyq
 * @create 2019-09-12 14:27
 */
@Service
public class LevelServiceImpl implements ILevelService {
    @Autowired
    private ILevelDao levelDao;

    public boolean checkSalary(double salary, int lid) throws Exception {
        Level level = levelDao.findById(lid);
        if(salary>=level.getLosal()&&salary<=level.getHisal()){
            return true;
        }
        return false;
    }
}
