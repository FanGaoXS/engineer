package com.blctek.carserver.controller;

import com.blctek.carserver.mapper.VehicleMapper;
import com.blctek.carserver.pojo.Car;
import com.blctek.carserver.pojo.Driver;
import com.blctek.carserver.pojo.Machine;
import com.blctek.carserver.pojo.Vehicle;
import com.blctek.carserver.service.VehicleService;
import com.blctek.carserver.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2020/12/28/11:36
 * @Description:
 */
@RestController
@RequestMapping("vehicle")
@Slf4j
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private VehicleMapper vehicleMapper;

    /**
     *  新增车辆信息（上传）
     * @param chipId 芯片编号
     * @param plateType 车牌类型（新能源、汽油车）
     * @param vehicleModel 车辆类型（皮卡车、罐车...）
     * @param plateNumber   车牌号
     * @param driverName    驾驶员姓名
     * @param driverPhone   驾驶员电话号码
     * @return              responseMap（状态码、数据、提示信息）
     */
    @GetMapping("/addVehicle/"
            + "{chipId}/"
            + "{plateType}/"
            + "{vehicleModel}/"
            + "{plateNumber}/"
            + "{driverName}/"
            + "{driverPhone}")
    public Boolean addVehicle(@PathVariable("chipId") String chipId,
                              @PathVariable("plateType") String plateType,
                              @PathVariable("vehicleModel") String vehicleModel,
                              @PathVariable("plateNumber") String plateNumber,
                              @PathVariable("driverName") String driverName,
                              @PathVariable("driverPhone") String driverPhone/*,
                              @RequestParam("file") MultipartFile file*/){
        log.info("想要新增车辆信息：");
        log.info("芯片编号->[{}]",chipId);
        log.info("车牌类型->[{}]",plateType);
        log.info("车辆类型->[{}]",vehicleModel);
        log.info("车牌号->[{}]",plateNumber);
        log.info("驾驶员姓名->[{}]",driverName);
        log.info("驾驶员手机号码->[{}]",driverPhone);
        Vehicle vehicleByPlateNumber = vehicleMapper.selectVehicleByPlateNumber(plateNumber); //根据车牌号从数据中查询有无该记录
        if (vehicleByPlateNumber!=null) {   //不为空，说明数据库中已经存在，则覆盖（删除原记录再新插入）
            vehicleService.removeVehicle(vehicleByPlateNumber.getVehicleId(),vehicleByPlateNumber.getCar().getCarId(),vehicleByPlateNumber.getDriver().getDriverId());
        }

        Car car = new Car();
        car.setChipId(chipId);

        Driver driver = new Driver();
        driver.setDriverName(driverName);
        driver.setDriverPhone(driverPhone);

        Vehicle vehicle = new Vehicle();
        vehicle.setPlateNumber(plateNumber);
        vehicle.setPlateType(plateType);
        vehicle.setVehicleModel(vehicleModel);

        try {
            vehicleService.addVehicle(car, driver, vehicle);
            log.info("上传车辆信息成功");
            return true;
        } catch (Exception e){
            e.printStackTrace();
            log.info("上传车辆信息成功");
            return false;
        }
    }

    /**
     *  查询所有车辆集合
     * @return  车辆集合
     */
    @GetMapping("/allVehicle")
    public List<Vehicle> allVehicle(){
        log.info("想要查询所有车辆");
        return vehicleService.findAllVehicle();
    }

    /**
     * 修改车辆信息
     * @param vehicle
     * @param driver
     * @return
     */
    @GetMapping("/modifyVehicle/"
            + "{vehicleId}/"
            + "{plateNumber}/"
            + "{plateType}/"
            + "{vehicleModel}/"
            + "{carId}/"
            + "{driverId}/"
            + "{driverName}/"
            + "{driverPhone}" )
    public Boolean modifyVehicle(Vehicle vehicle,
                                 Driver driver){
        log.info("想要修改车辆信息");
        log.info("修改车辆信息为->[{}]",vehicle.toString());
        log.info("修改驾驶员信息为->[{}]",driver.toString());
        try {
            vehicleService.modifyVehicleAndDriverInfo(vehicle, driver);
            log.info("修改车辆信息成功");
            return true;
        } catch (Exception e){
            e.printStackTrace();
            log.info("修改车辆信息失败");
            return false;
        }
    }

    /**
     * 删除车辆信息
     * @param vehicleId
     * @param carId
     * @param driverId
     * @return
     */
    @GetMapping("/removeVehicle/{vehicleId}/{carId}/{driverId}")
    public Boolean removeVehicle(@PathVariable("vehicleId") Integer vehicleId,
                                 @PathVariable("carId") Integer carId,
                                 @PathVariable("driverId") Integer driverId){
        log.info("想要删除车辆信息");
        log.info("机械编号->[{}]",vehicleId);
        log.info("所属工程用具编号->[{}]",carId);
        log.info("驾驶员编号->[{}]",driverId);
        try {
            vehicleService.removeVehicle(vehicleId, carId, driverId);
            log.info("删除车辆信息成功");
            return true;
        } catch (Exception e){
            e.printStackTrace();
            log.info("删除车辆信息失败");
            return false;
        }
    }
}
