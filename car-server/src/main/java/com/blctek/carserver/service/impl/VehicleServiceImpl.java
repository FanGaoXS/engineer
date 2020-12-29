package com.blctek.carserver.service.impl;

import com.blctek.carserver.mapper.CarMapper;
import com.blctek.carserver.mapper.DriverMapper;
import com.blctek.carserver.mapper.VehicleMapper;
import com.blctek.carserver.pojo.Car;
import com.blctek.carserver.pojo.Driver;
import com.blctek.carserver.pojo.Vehicle;
import com.blctek.carserver.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2020/12/25/17:32
 * @Description:
 */
@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    VehicleMapper vehicleMapper;
    @Autowired
    DriverMapper driverMapper;
    @Autowired
    CarMapper carMapper;
    @Override
    public List<Vehicle> findAllVehicle() {
        return vehicleMapper.selectAllVehicleAndCarAndDriver();
    }

    @Override
    public Vehicle addVehicle(Car car,Driver driver,Vehicle vehicle) {
        /**
         *  因为新增vehicle信息需要同时插入三张表
         *  所以先插入driver表，然后获得driverId
         *  再将driverId和car对象组合起来插入car表
         *  就能够得到carId，最后再将carId和vehicle的
         *  信息一起插入vehicle表，同时利用Spring的事务
         *  如果其中一个插入失败，那么三个插入都回滚
         */
        try {

            driverMapper.insertDriver(driver); //尝试向driver表中插入driver对象
            Integer driverId = driver.getDriverId(); //若插入成功则能够获取到driverId

            car.setDriverId(driverId); // 将driver表中的driverId赋给car
            car.setType("车辆");
            carMapper.insertCar(car); //尝试向car表中插入car对象
            Integer carId = car.getCarId(); //若插入成功则能够获取到carId

            vehicle.setCarId(carId); // 将car表中的carId赋给vehicle
            vehicleMapper.insertVehicle(vehicle); //尝试向vehicle表中插入vehicle对象

            return vehicle;
        } catch (DataAccessException e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println(e);
            return null;
        }
    }

    @Override
    public Boolean modifyVehicleAndDriverInfo(Vehicle vehicle, Driver driver) {
        try {
            vehicleMapper.updateVehicle(vehicle);
            driverMapper.updateDriver(driver);
            return true;
        }catch (DataAccessException e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println(e);
            return false;
        }
    }

    @Override
    public Boolean removeVehicle(Integer vehicleId, Integer carId, Integer driverId) {
        try {
            vehicleMapper.deleteVehicle(vehicleId);
            carMapper.deleteCar(carId);
            driverMapper.deleteDriver(driverId);
            return true;
        } catch (DataAccessException e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println(e);
            return false;
        }
    }

}
