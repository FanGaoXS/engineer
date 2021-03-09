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
    public Engineer insertVehicleAndDriver(Engineer engineer, Driver driver) {
        try {
            driver.setUuid(UUID.randomUUID().toString());//设置driver的uuid
            driverMapper.insertOne(driver);//向driver表中插入（如果插入成功能够得到id）

            engineer.setUuid(UUID.randomUUID().toString());//设置engineer的uuid
            engineer.setType("车辆");//设置类型为车辆
            engineer.setDriverId(driver.getId());//得到刚刚插入成功的driver对象的id
            engineer.setInputTime(new Date());//设置时间
            engineer.setMachineNumber(null);
            engineer.setEngineNumber(null);
            engineerMapper.insertOne(engineer);//向engineer表中插入

            engineer.setDriver(driver);//将成功插入的driver对象附着到engineer对象上并返回
            return engineer;
        } catch (DataAccessException e){    //插入失败
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//事务回滚
            e.printStackTrace();
            return null;//否则返回null
        }
    }

    @Override
    public Engineer insertMachineAndDriver(Engineer engineer, Driver driver) {
        try {
            driver.setUuid(UUID.randomUUID().toString());//设置driver的uuid
            driverMapper.insertOne(driver);//向driver表中插入（如果插入成功能够得到id）

            engineer.setUuid(UUID.randomUUID().toString());//设置engineer的uuid
            engineer.setType("机械");//设置类型为车辆
            engineer.setDriverId(driver.getId());//得到刚刚插入成功的driver对象的id
            engineer.setInputTime(new Date());//设置时间
            engineer.setVehicleNumber(null);
            engineer.setPlateType(null);
            engineerMapper.insertOne(engineer);//向engineer表中插入

            engineer.setDriver(driver);//将成功插入的driver对象附着到engineer对象上并返回
            return engineer;
        } catch (DataAccessException e){    //插入失败
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//事务回滚
            e.printStackTrace();
            return null;//否则返回null
        }
    }
}
