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
@CrossOrigin(origins = "*")
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
     * @param file          上传的图片
     * @return              responseMap（状态码、数据、提示信息）
     */
    @PostMapping("/addVehicle")
    public Map<String,Object> addVehicle(@RequestParam("chipId") String chipId,
                                         @RequestParam("plateType") String plateType,
                                         @RequestParam("vehicleModel") String vehicleModel,
                                         @RequestParam("plateNumber") String plateNumber,
                                         @RequestParam("driverName") String driverName,
                                         @RequestParam("driverPhone") String driverPhone,
                                         @RequestParam("file") MultipartFile file){
        log.info("---上传车辆信息 start---");
        log.info("芯片编号->[{}]",chipId);
        log.info("车牌类型->[{}]",plateType);
        log.info("车辆类型->[{}]",vehicleModel);
        log.info("车牌号->[{}]",plateNumber);
        log.info("驾驶员姓名->[{}]",driverName);
        log.info("驾驶员手机号码->[{}]",driverPhone);
        log.info("文件是否为空->[{}]",file.isEmpty());
        String fileSize=new String();
        if (file.getSize()>0&&file.getSize()<=(1024*1024)){ //如果文件大小小于1M
            fileSize=file.getSize()/1024+"Kb"; //以kb显示
        } else if (file.getSize()>(1024*1024)){// 如果文件大小大于1M
            fileSize=file.getSize()/1024/1024+"M"; //以显示M
        }
        log.info("文件大小->[{}]",fileSize);

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

        Vehicle vehicleDB = vehicleService.addVehicle(car, driver, vehicle);

        ResponseEntity<String> responseEntity = HttpUtils.updateDevByPut(chipId, plateNumber);
        log.info("updateDevByPut.statusCode->[{}]",responseEntity.getStatusCode());

        HashMap<String, Object> resMap = new HashMap<>();
        resMap.put("status",true);
        resMap.put("statusCode",responseEntity.getStatusCode());
        resMap.put("msg","上传车辆信息成功！");
        resMap.put("data",vehicleDB);
        log.info("---uploadVehicle end---");
        return resMap;
    }

    /**
     *  查询所有车辆集合
     * @return  responseMap（状态码、数据、提示信息）
     */
    @GetMapping("/allVehicle")
    public Map<String,Object> allVehicle(){
        List<Vehicle> vehicleList = vehicleService.findAllVehicle();
        HashMap<String, Object> resMap = new HashMap<>();
        resMap.put("status",true);
        resMap.put("msg","查询所有车辆信息");
        resMap.put("data",vehicleList);
        return resMap;
    }

    /**
     * 修改车辆信息
     * @param vehicle
     * @param driver
     * @return
     */
    @GetMapping("/modifyVehicle")
    public Map<String,Object> modifyVehicle(Vehicle vehicle,Driver driver){
        Boolean result = vehicleService.modifyVehicleAndDriverInfo(vehicle, driver);
        log.info("Vehicle是否修改成功->[{}]",result?"是":"否");
        HashMap<String, Object> resMap = new HashMap<>();
        resMap.put("status",result);
        resMap.put("msg","修改车辆信息");
        return resMap;
    }

    /**
     * 删除车辆信息
     * @param vehicleId
     * @param carId
     * @param driverId
     * @return
     */
    @GetMapping("/removeVehicle")
    public Map<String,Object> removeVehicle(Integer vehicleId,
                                            Integer carId,
                                            Integer driverId){
        log.info("机械编号->[{}]",vehicleId);
        log.info("所属工程用具编号->[{}]",carId);
        log.info("驾驶员编号->[{}]",driverId);
        Boolean result = vehicleService.removeVehicle(vehicleId, carId, driverId);
        log.info("Vehicle是否删除成功->[{}]",result?"是":"否");
        HashMap<String, Object> resMap = new HashMap<>();
        resMap.put("status",result);
        resMap.put("msg","删除车辆信息");
        return resMap;
    }
}
