package com.xyq.service;

/**
 * @Author xyq
 * @create 2019-09-12 14:25
 */
public interface ILevelService {
    /**
     * 检测制定的工资是否在指定的雇员等级之中
     * @param salary 输入的工资数据
     * @param lid 雇员等级信息
     * @return 如果满足等级验证返回true,否则返回false
     * @throws Exception
     */
    public boolean checkSalary(double salary,int lid)throws Exception;
}
