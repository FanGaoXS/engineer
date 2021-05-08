package com.fangaoxs.engineserver.service.impl;

import com.fangaoxs.engineserver.mapper.DriverMapper;
import com.fangaoxs.engineserver.mapper.EngineerMapper;
import com.fangaoxs.engineserver.pojo.Driver;
import com.fangaoxs.engineserver.pojo.Engineer;
import com.fangaoxs.engineserver.service.EngineerService;
import com.fangaoxs.engineserver.vo.VoEngineer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.List;

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
    public Boolean insertEngineerAndDriver(Driver driver, Engineer engineer) {
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
            return engineerMapper.deleteOne(engineer)>0;//如果成功删除的记录数大于0则说明删除成功
        } catch (DataAccessException e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//事务回滚
            e.printStackTrace();
            return false;//失败
        }
    }

    @Override
    public Boolean updateEngineerAndDriver(Driver driver,Engineer engineer) {
        try {
            engineerMapper.updateOne(engineer);
            driverMapper.updateOne(driver);
            return true;
        } catch (DataAccessException e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//事务回滚
            e.printStackTrace();
            return false;//失败
        }
    }

    @Override
    public List<VoEngineer> selectListByType(String type, Integer currentPage, Integer pageSize) {
        Engineer engineer = new Engineer();
        engineer.setType(type);
        engineer.setCurrentPage(currentPage);
        engineer.setPageSize(pageSize);
        List<Engineer> engineerList = engineerMapper.selectList(engineer);
        ArrayList<VoEngineer> voEngineerList = new ArrayList<>();
        engineerList.forEach(dbEngineer -> voEngineerList.add(new VoEngineer(dbEngineer)));
        return voEngineerList;
    }

    @Override
    public Engineer selectVehicleByVehicleNumber(String vehicleNumber) {
        Engineer engineer = new Engineer();
        engineer.setVehicleNumber(vehicleNumber);
        return engineerMapper.selectOne(engineer);
    }

    @Override
    public Engineer selectMachineByEngineerNumber(String engineNumber) {
        Engineer engineer = new Engineer();
        engineer.setVehicleNumber(engineNumber);
        return engineerMapper.selectOne(engineer);
    }

    @Override
    public Engineer selectEngineerById(Integer id) {
        Engineer engineer = new Engineer();
        engineer.setId(id);
        return engineerMapper.selectOne(engineer);
    }


    @Override
    public Long selectTotalSize(Engineer engineer) {
        return engineerMapper.count(engineer);
    }
}
