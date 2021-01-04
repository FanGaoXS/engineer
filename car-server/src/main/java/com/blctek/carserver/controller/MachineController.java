package com.blctek.carserver.controller;

import com.blctek.carserver.pojo.Car;
import com.blctek.carserver.pojo.Driver;
import com.blctek.carserver.pojo.Machine;
import com.blctek.carserver.service.MachineService;
import com.blctek.carserver.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("machine")
@Slf4j
public class MachineController {

    @Autowired
    private MachineService machineService;

    /**
     *  新增机械信息（上传）
     * @param chipId    芯片编号
     * @param machineModel  机械类型（挖机、压路机）
     * @param machineNumber 机械型号
     * @param engineNumber  发动机编号
     * @param driverName    驾驶员姓名
     * @param driverPhone   驾驶员电话号码
     * @param file          上传的图片文件
     * @return              responseMap（状态码、数据、提示信息）
     */
    @PostMapping("/addMachine")
    public Map<String,Object> addMachine(@RequestParam("chipId") String chipId,
                                         @RequestParam("machineModel") String machineModel,
                                         @RequestParam("machineNumber") String machineNumber,
                                         @RequestParam("engineNumber") String engineNumber,
                                         @RequestParam("driverName") String driverName,
                                         @RequestParam("driverPhone") String driverPhone,
                                         @RequestParam("file") MultipartFile file){
        log.info("---上传机械信息 start---");
        log.info("芯片编号->[{}]",chipId);
        log.info("机械类型->[{}]",machineModel);
        log.info("机械型号->[{}]",machineNumber);
        log.info("机械发动机编号->[{}]",engineNumber);
        log.info("驾驶员姓名->[{}]",driverName);
        log.info("驾驶员手机号码->[{}]",driverPhone);
        log.info("文件是否为空->[{}]",file.isEmpty());
        String fileSize = new String();
        if (file.getSize()>0&&file.getSize()<=(1024*1024)){ //如果文件大小小于1M
            fileSize = file.getSize()/1024+"Kb"; //以kb显示
        } else if (file.getSize()>(1024*1024)){// 如果文件大小大于1M
            fileSize = file.getSize()/1024/1024+"M"; //以显示M
        }
        log.info("文件大小->[{}]",fileSize);

        Car car = new Car();
        car.setChipId(chipId);

        Driver driver = new Driver();
        driver.setDriverName(driverName);
        driver.setDriverPhone(driverPhone);

        Machine machine = new Machine();
        machine.setMachineNumber(machineNumber);
        machine.setEngineNumber(engineNumber);
        machine.setMachineModel(machineModel);

        Machine machineDB = machineService.addMachine(car,driver,machine);

        // HttpUtils.updateDevByPut(chipId,engineNumber);

        HashMap<String, Object> resMap = new HashMap<>();
        resMap.put("status",true);
        resMap.put("msg","上传机械信息成功");
        resMap.put("data",machineDB);
        log.info("---上传车辆信息 end---");
        return resMap;
    }

    /**
     *  查询所有机械集合
     * @return  responseMap（状态码、数据、提示信息）
     */
    @GetMapping("/allMachine")
    public Map<String,Object> allMachine(){
        List<Machine> machineList = machineService.findAllMachine();
        HashMap<String, Object> resMap = new HashMap<>();
        resMap.put("status",true);
        resMap.put("msg","查询所有机械集合");
        resMap.put("data",machineList);
        return resMap;
    }

    /**
     *  修改机械信息
     * @param machine
     * @param driver
     * @return
     */
    @GetMapping("/modifyMachine")
    public Map<String,Object> modifyMachine(Machine machine,Driver driver){
        Boolean result = machineService.modifyMachineAndDriverInfo(machine,driver);
        log.info("Machine是否修改成功->[{}]",result?"是":"否");
        HashMap<String, Object> resMap = new HashMap<>();
        resMap.put("status",result);
        resMap.put("msg","修改机械信息");
        return resMap;
    }

    /**
     *  删除机械信息
     * @param machineId
     * @param carId
     * @param driverId
     * @return
     */
    @GetMapping("/removeMachine")
    public Map<String,Object> removeMachine(Integer machineId,
                                            Integer carId,
                                            Integer driverId){
        log.info("机械编号->[{}]",machineId);
        log.info("所属工程用具编号->[{}]",carId);
        log.info("驾驶员编号->[{}]",driverId);
        Boolean result = machineService.removeMachine(machineId, carId, driverId);
        log.info("Machine是否删除成功->[{}]",result?"是":"否");
        HashMap<String, Object> resMap = new HashMap<>();
        resMap.put("status",result);
        resMap.put("msg","删除机械信息");
        return resMap;
    }
}
