package com.blctek.authserver.controller;

import com.blctek.authserver.service.CarHttpService;
import com.blctek.authserver.service.DevHttpService;
import com.blctek.carserver.pojo.Driver;
import com.blctek.carserver.pojo.Machine;
import com.blctek.carserver.pojo.Model;
import com.blctek.carserver.pojo.Vehicle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

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

    @PostMapping("/uploadSpeedTest")
    public Map<String,Object> uploadSpeedTest(HttpServletRequest request){
        /*log.info("http从客户端发起的时间戳->[{}]",clientTime);
        long serverTime = new Date().getTime() ;
        log.info("http请求到客户端的时间戳->[{}]",serverTime);
        double diffTime = (double)serverTime - clientTime; //客户端到服务端的延迟（单位ms）
        log.info("客户端到服务端的延迟->[{}ms]",diffTime);
        System.out.println(diffTime/1000);
        double contentLengthLong = request.getContentLengthLong(); //http请求的长度（单位为Byte）
        double speed = contentLengthLong / (diffTime / 1000); //每秒钟发送的多少字节（单位Byte/s）
        log.info("（标准算法）客户端向服务端发送请求的速率->[{}Byte/s]",speed);
        log.info("（国家带宽算法）客户端向服务端发送请求的速率->[{}Bps]",speed * 8);
        log.info("（国家带宽算法）客户端向服务端发送请求的速率->[{}KBps]",(speed * 8) / 1024);
        log.info("（国家带宽算法）客户端向服务端发送请求的速率->[{}MBps]",(speed * 8) / 1024 / 1024);
        HashMap<String, Object> resMap = new HashMap<>();
        resMap.put("data",speed);*/
        long contentLength = request.getContentLengthLong();
        long endTime = new Date().getTime();
        HashMap<String, Object> dataMap = new HashMap<>();
        dataMap.put("contentLength",contentLength);
        log.info("contentLength->[{} Byte]",contentLength);
        dataMap.put("endTime",endTime);
        HashMap<String, Object> resMap = new HashMap<>();
        resMap.put("msg","客户端到服务端");
        resMap.put("data",dataMap);
        return resMap;
    }

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
                                            Driver driver,
                                            String chipId){
        log.info("修改车辆信息");
        log.info("芯片编号->[{}]",chipId);
        log.info("车辆信息->[{}]",vehicle.toString());
        log.info("驾驶员信息->[{}]",driver.toString());
        Boolean result = carHttpService.vehicleUpdate(vehicle, driver);
        HashMap<String, Object> resMap = new HashMap<>();
        ResponseEntity<String> responseEntity = devHttpService.devInsert(chipId, vehicle.getPlateNumber());
        log.info("插入到轨迹服务器的statusCode->[{}]",responseEntity.getStatusCode());
        resMap.put("statusCode",responseEntity.getStatusCode());
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
                                         @RequestParam("imagePath") String imagePath){
        log.info("新增车辆");
        log.info("设备编号->[{}]",chipId);
        log.info("车牌类型->[{}]",plateType);
        log.info("车辆类型->[{}]",vehicleModel);
        log.info("车牌号->[{}]",plateNumber);
        log.info("司机姓名->[{}]",driverName);
        log.info("司机电话号码->[{}]",driverPhone);
        log.info("图片路径->[{}]",imagePath);
        Boolean result = carHttpService.vehicleInsert(chipId, plateType, vehicleModel, plateNumber, driverName, driverPhone,imagePath);
        HashMap<String, Object> resMap = new HashMap<>();
        resMap.put("msg","新增车辆信息");
        resMap.put("status",result);
        ResponseEntity<String> responseEntity = devHttpService.devInsert(chipId, plateNumber);
        log.info("插入到轨迹服务器的statusCode->[{}]",responseEntity.getStatusCode());
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
                                         @RequestParam("imagePath") String imagePath){
        log.info("想要新增机械");
        log.info("芯片编号->[{}]",chipId);
        log.info("机械类型->[{}]",machineModel);
        log.info("机械型号->[{}]",machineNumber);
        log.info("机械发动机编号->[{}]",engineNumber);
        log.info("驾驶员姓名->[{}]",driverName);
        log.info("驾驶员手机号码->[{}]",driverPhone);
        Boolean result = carHttpService.machineInsert(chipId, machineModel, machineNumber, engineNumber, driverName, driverPhone,imagePath);
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

    @GetMapping("/insertModel")
    public Map<String,Object> insertModel(Model model){
        log.info("想要新增类型");
        log.info("类型->[{}]",model.toString());
        Boolean result = carHttpService.insertModel(model);
        HashMap<String, Object> resMap = new HashMap<>();
        resMap.put("status",result);
        resMap.put("msg","新增类型");
        return resMap;
    }

    @GetMapping("/modelList")
    public Map<String,Object> modelList(){
        log.info("想要查看所有类型");
        HashMap<String, Object> resMap = new HashMap<>();
        resMap.put("status",true);
        resMap.put("data",carHttpService.modelList());
        resMap.put("msg","查询所有类型");
        return resMap;
    }

    @GetMapping("/modelListByBelong")
    public Map<String,Object> modelListByBelong(String modelBelong){
        log.info("想要查询属于[{}]的所有类型",modelBelong);
        HashMap<String, Object> resMap = new HashMap<>();
        resMap.put("status",true);
        resMap.put("data",carHttpService.modelListByBelong(modelBelong));
        resMap.put("msg","查询属于"+modelBelong+"的所有类型");
        return resMap;
    }

    @GetMapping("/deleteModel")
    public Map<String,Object> deleteModel(Integer id){
        log.info("想要删除编号为[{}]的类型",id);
        HashMap<String, Object> resMap = new HashMap<>();
        resMap.put("status",carHttpService.deleteModel(id));
        resMap.put("msg","删除编号为["+id+"]的类型");
        return resMap;
    }

    @GetMapping("/updateModel")
    public Map<String,Object> updateModel(Model model){
        log.info("想要修改类型为->[{}]",model.toString());
        HashMap<String, Object> resMap = new HashMap<>();
        resMap.put("status",carHttpService.updateModel(model));
        resMap.put("msg","修改所属["+model.getModelBelong()+"]的["+model.getModelName()+"]类型");
        return resMap;
    }

}
