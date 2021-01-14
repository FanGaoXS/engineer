package com.blctek.carserver.controller;

import com.blctek.carserver.pojo.Car;
import com.blctek.carserver.pojo.Driver;
import com.blctek.carserver.pojo.Machine;
import com.blctek.carserver.service.MachineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2020/12/28/11:36
 * @Description:
 */
@RestController
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
     * @return
     */
    @GetMapping("/addMachine/" +
            "{chipId}/" +
            "{machineModel}/" +
            "{machineNumber}/" +
            "{engineNumber}/" +
            "{driverName}/" +
            "{driverPhone}" +
            "{imagePath}")
    public Boolean addMachine(@PathVariable("chipId") String chipId,
                              @PathVariable("machineModel") String machineModel,
                              @PathVariable("machineNumber") String machineNumber,
                              @PathVariable("engineNumber") String engineNumber,
                              @PathVariable("driverName") String driverName,
                              @PathVariable("driverPhone") String driverPhone,
                              @PathVariable("imagePath") String imagePath){
        log.info("想要上传机械信息");
        log.info("芯片编号->[{}]",chipId);
        log.info("机械类型->[{}]",machineModel);
        log.info("机械型号->[{}]",machineNumber);
        log.info("机械发动机编号->[{}]",engineNumber);
        log.info("驾驶员姓名->[{}]",driverName);
        log.info("驾驶员手机号码->[{}]",driverPhone);
        log.info("机械图片的路径->[{}]",imagePath);

        Car car = new Car();
        car.setChipId(chipId);
        car.setImagePath(imagePath);

        Driver driver = new Driver();
        driver.setDriverName(driverName);
        driver.setDriverPhone(driverPhone);

        Machine machine = new Machine();
        machine.setMachineNumber(machineNumber);
        machine.setEngineNumber(engineNumber);
        machine.setMachineModel(machineModel);

        try {
            machineService.addMachine(car,driver,machine);
            log.info("上传机械信息成功");
            return true;
        } catch (Exception e){
            e.printStackTrace();
            log.info("上传机械信息失败");
            return false;
        }
    }

    /**
     *  查询所有机械集合
     * @return  responseMap（状态码、数据、提示信息）
     */
    @GetMapping("/allMachine")
    public List<Machine> allMachine(){
        log.info("查询所有机械");
        return machineService.findAllMachine();
    }

    /**
     *  修改机械信息
     * @param machine
     * @param driver
     * @return
     */
    @GetMapping("/modifyMachine/" +
            "{machineId}/" +
            "{machineNumber}/" +
            "{engineNumber}/" +
            "{machineModel}/" +
            "{driverId}/" +
            "{driverName}/" +
            "{driverPhone}")
    public Boolean modifyMachine(Machine machine,
                                 Driver driver){
        log.info("修改机械信息");
        log.info("机械信息->[{}]",machine.toString());
        log.info("驾驶员信息->[{}]",driver.toString());
        try {
            machineService.modifyMachineAndDriverInfo(machine, driver);
            log.info("修改机械信息成功");
            return true;
        } catch (Exception e){
            e.printStackTrace();
            log.info("修改机械信息失败");
            return false;
        }
    }

    /**
     *  删除机械信息
     * @param machineId
     * @param carId
     * @param driverId
     * @return
     */
    @GetMapping("/removeMachine/" +
            "{machineId}/" +
            "{carId}/" +
            "{driverId}")
    public Boolean removeMachine(@PathVariable("machineId") Integer machineId,
                                 @PathVariable("carId") Integer carId,
                                 @PathVariable("driverId") Integer driverId){
        log.info("删除机械信息");
        log.info("机械编号->[{}]",machineId);
        log.info("所属工程用具编号->[{}]",carId);
        log.info("驾驶员编号->[{}]",driverId);
        try {
            machineService.removeMachine(machineId, carId, driverId);
            log.info("删除机械信息成功");
            return true;
        } catch (Exception e){
            log.info("删除机械信息失败");
            return false;
        }
    }
}
