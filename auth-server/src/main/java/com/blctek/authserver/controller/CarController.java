package com.blctek.authserver.controller;

import com.blctek.authserver.service.CarHttpService;
import com.blctek.authserver.service.DevHttpService;
import com.blctek.carserver.pojo.Driver;
import com.blctek.carserver.pojo.Machine;
import com.blctek.carserver.pojo.Vehicle;
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
 * @Date: 2021/01/09/16:55
 * @Description:
 */
@RestController
@RequestMapping("car")
@Slf4j
@CrossOrigin("*")
public class CarController {

    @Autowired
    CarHttpService carHttpService;
    @Autowired
    DevHttpService devHttpService;

    @GetMapping("/allVehicle")
    public Map<String,Object> vehicleList(){
        HashMap<String, Object> resMap = new HashMap<>();
        resMap.put("msg","查询所有车辆");
        try {
            List<Vehicle> vehicleList = carHttpService.vehicleList();
            resMap.put("status",true);
            resMap.put("data",vehicleList);

            return resMap;
        } catch (Exception e){
            e.printStackTrace();
            resMap.put("status",false);
            return resMap;
        }
    }

    @GetMapping("/removeVehicle")
    public Map<String,Object> vehicleRemove(@RequestParam("vehicleId") Integer vehicleId,
                                            @RequestParam("carId") Integer carId,
                                            @RequestParam("driverId") Integer driverId){
        log.info("删除车辆信息");
        log.info("车辆编号->[{}]",vehicleId);
        log.info("所属工程车辆编号->[{}]",carId);
        log.info("驾驶员编号->[{}]",driverId);
        Boolean result = carHttpService.vehicleDelete(vehicleId, carId, driverId);
        HashMap<String, Object> resMap = new HashMap<>();
        resMap.put("msg","删除车辆信息");
        resMap.put("status",result);
        return resMap;

    }


    @GetMapping("/modifyVehicle")
    public Map<String,Object> vehicleModify(Vehicle vehicle,
                                            Driver driver){
        log.info("修改车辆信息");
        log.info("车辆信息->[{}]",vehicle.toString());
        log.info("驾驶员信息->[{}]",driver.toString());
        Boolean result = carHttpService.vehicleUpdate(vehicle, driver);
        HashMap<String, Object> resMap = new HashMap<>();
        resMap.put("msg","修改车辆信息");
        resMap.put("status",result);
        return resMap;

    }

    @PostMapping("/addVehicle")
    public Map<String,Object> vehicleAdd(@RequestParam("chipId") String chipId,
                                         @RequestParam("plateType") String plateType,
                                         @RequestParam("vehicleModel") String vehicleModel,
                                         @RequestParam("plateNumber") String plateNumber,
                                         @RequestParam("driverName") String driverName,
                                         @RequestParam("driverPhone") String driverPhone,
                                         @RequestParam("file") MultipartFile file){
        log.info("新增车辆");
        log.info("设备编号->[{}]",chipId);
        log.info("车牌类型->[{}]",plateType);
        log.info("车辆类型->[{}]",vehicleModel);
        log.info("车牌号->[{}]",plateNumber);
        log.info("司机姓名->[{}]",driverName);
        log.info("司机电话号码->[{}]",driverPhone);
        log.info("文件大小->[{}]",file.getSize());
        Boolean result = carHttpService.vehicleInsert(chipId, plateType, vehicleModel, plateNumber, driverName, driverPhone);
        ResponseEntity<String> responseEntity = devHttpService.devInsert(chipId, plateNumber);
        HashMap<String, Object> resMap = new HashMap<>();
        resMap.put("msg","新增车辆信息");
        resMap.put("status",result);
        resMap.put("statusCode",responseEntity.getStatusCode());
        return resMap;
    }

    @PostMapping("/addMachine")
    public Map<String,Object> machineAdd(@RequestParam("chipId") String chipId,
                                         @RequestParam("machineModel") String machineModel,
                                         @RequestParam("machineNumber") String machineNumber,
                                         @RequestParam("engineNumber") String engineNumber,
                                         @RequestParam("driverName") String driverName,
                                         @RequestParam("driverPhone") String driverPhone,
                                         @RequestParam("file") MultipartFile file){
        log.info("想要新增机械");
        log.info("芯片编号->[{}]",chipId);
        log.info("机械类型->[{}]",machineModel);
        log.info("机械型号->[{}]",machineNumber);
        log.info("机械发动机编号->[{}]",engineNumber);
        log.info("驾驶员姓名->[{}]",driverName);
        log.info("驾驶员手机号码->[{}]",driverPhone);

        Boolean result = carHttpService.machineInsert(chipId, machineModel, machineNumber, engineNumber, driverName, driverPhone);
        HashMap<String, Object> resMap = new HashMap<>();
        resMap.put("msg","新增机械信息");
        resMap.put("status",result);
        return resMap;
    }

    @GetMapping("/removeMachine")
    public Map<String,Object> machineRemove(@RequestParam("machineId")Integer machineId,
                                            @RequestParam("carId")Integer carId,
                                            @RequestParam("driverId")Integer driverId){
        log.info("想要删除机械信息");
        log.info("机械编号->[{}]",machineId);
        log.info("所属工程用具编号->[{}]",carId);
        log.info("驾驶员编号->[{}]",driverId);
        Boolean result = carHttpService.machineDelete(machineId, carId, driverId);
        HashMap<String, Object> resMap = new HashMap<>();
        resMap.put("status",result);
        resMap.put("msg","删除机械信息");
        return resMap;
    }

    @GetMapping("/modifyMachine")
    public Map<String,Object> machineModify(Machine machine,Driver driver){
        log.info("想要修改机械信息");
        log.info("机械信息->[{}]",machine.toString());
        log.info("驾驶员信息->[{}]",driver.toString());
        Boolean result = carHttpService.machineUpdate(machine, driver);
        HashMap<String, Object> resMap = new HashMap<>();
        resMap.put("status",result);
        resMap.put("msg","修改机械信息");
        return resMap;
    }

    @GetMapping("/allMachine")
    public Map<String,Object> machineList(){
        log.info("想要查询所有机械信息");
        List<Machine> machineList = carHttpService.machineList();
        HashMap<String, Object> resMap = new HashMap<>();
        resMap.put("status",true);
        resMap.put("msg","查询所有机械信息");
        resMap.put("data",machineList);
        return resMap;
    }

}
