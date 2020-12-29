package com.blctek.carserver.service;

import com.blctek.carserver.pojo.Car;
import com.blctek.carserver.pojo.Driver;
import com.blctek.carserver.pojo.Vehicle;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2020/12/25/17:25
 * @Description:
 */
@Service
public interface VehicleService {

    /**
     *  查询所有车辆信息
     * @return 车辆、工程用具、驾驶员信息
     */
    List<Vehicle> findAllVehicle();

    /**
     *  新增车辆
     * @param car
     * @param driver
     * @param vehicle
     * @return 新增成功后的车辆信息对象
     */
    Vehicle addVehicle(Car car,Driver driver,Vehicle vehicle);

    /**
     *  修改车辆以及它的驾驶员信息
     * @param vehicle   车辆对象
     * @param driver    驾驶员对象
     * @return  true成功，false失败
     */
    Boolean modifyVehicleAndDriverInfo(Vehicle vehicle,Driver driver);

    /**
     *  删除车辆信息
     * @param vehicleId 车辆编号
     * @param carId 所属工程用具编号
     * @param driverId  驾驶员编号
     * @return  true成功，false失败
     */
    Boolean removeVehicle(Integer vehicleId,
                          Integer carId,
                          Integer driverId);
}
