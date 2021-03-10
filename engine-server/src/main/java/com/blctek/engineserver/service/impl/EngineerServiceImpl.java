package com.blctek.engineserver.service.impl;

import com.blctek.engineserver.mapper.DriverMapper;
import com.blctek.engineserver.mapper.EngineerMapper;
import com.blctek.engineserver.pojo.Driver;
import com.blctek.engineserver.pojo.Engineer;
import com.blctek.engineserver.service.EngineerService;
import org.apache.ibatis.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/09/17:27
 * @Description:
 */
@Service
@Transactional
public class EngineerServiceImpl implements EngineerService {
    @Autowired
    private EngineerMapper engineerMapper;
    @Autowired
    private DriverMapper driverMapper;

    @Override
    public Boolean insertEngineerAndDriver(Driver driver,Engineer engineer) {
        try {
            driverMapper.insertOne(driver); //向driver表中插入
            Integer driverId = driver.getId();//插入成功会得到driverId
            engineer.setDriverId(driverId);//将刚刚成功插入的driverId
            engineerMapper.insertOne(engineer);//向engineer表中插入
            //engineer.setDriver(driver);//将刚刚成功插入的driver对象附着到engineer对象上
            //return engineer;
            return true;//成功
        } catch (DataAccessException e){//插入失败
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//事务回滚
            e.printStackTrace();
            return false;//失败
        }
    }

    @Override
    public Boolean deleteEngineer(Integer id) {
        try {
            Engineer engineer = new Engineer();
            engineer.setId(id);
            System.out.println(engineerMapper.deleteOne(engineer));
            return engineerMapper.deleteOne(engineer)>0;//如果成功删除的记录数大于0则说明删除成功
        } catch (DataAccessException e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//事务回滚
            e.printStackTrace();
            return false;//失败
        }
    }

    @Override
    public Boolean updateEngineer(Engineer engineer) {
        try {
            return engineerMapper.updateOne(engineer) > 0;//如果成功修改的记录数大于0则说明修改成功
        } catch (DataAccessException e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//事务回滚
            e.printStackTrace();
            return false;//失败
        }
    }

    @Override
    public List<Engineer> selectList(Engineer engineer) {
        return engineerMapper.selectList(engineer);
    }

    @Override
    public Engineer selectOne(Engineer engineer) {
        return engineerMapper.selectOne(engineer);
    }

    @Override
    public Long selectTotalSize(Engineer engineer) {
        return engineerMapper.count(engineer);
    }
}
