package com.xyq.service.impl;

import com.xyq.dao.IEmpDao;
import com.xyq.dao.ILevelDao;
import com.xyq.service.IAdminService;
import com.xyq.vo.Dept;
import com.xyq.vo.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xyq
 * @create 2019-09-10 20:07
 */
@Service
public class AdminServiceImpl implements IAdminService {
    @Autowired
    private ILevelDao levelDao;
    @Autowired
    private IEmpDao empDao;

    public Map<String, Object> addPre() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("allLevels",levelDao.findAll());
        return map;
    }

    public boolean add(Emp vo) throws Exception {
        vo.setAflag(2);
        vo.setDept(new Dept());
        vo.getDept().setDid(1);
        return empDao.doCreate(vo);
    }
}
