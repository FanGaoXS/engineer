package com.blctek.engineserver.controller;

import com.blctek.commonserver.response.ResultResponse;
import com.blctek.engineserver.anno.CrudLog;
import com.blctek.engineserver.pojo.Driver;
import com.blctek.engineserver.pojo.Engineer;
import com.blctek.engineserver.service.EngineerService;
import com.blctek.engineserver.vo.VoDriver;
import com.blctek.engineserver.vo.VoEngineer;
import com.blctek.engineserver.vo.VoEngineerList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/10/14:16
 * @Description:    controller层：对工程用具的新增、删除、修改、查询
 */
@RestController
@RequestMapping("/engineer")
@CrossOrigin("*")
public class EngineerController {
    @Autowired
    private EngineerService engineerService;

    @CrudLog("新增车辆及其驾驶员信息")
    @PostMapping("/insertVehicle")
    public ResultResponse insertVehicle(@RequestBody VoEngineer voEngineer){
//        System.out.println("voEngineer = " + voEngineer);
        if (voEngineer.getVehicleNumber()!=null && (!voEngineer.getVehicleNumber().equals(""))){
            //车牌号不为空需要判断数据库中原本是否存在该车辆
            Engineer dbEngineer = engineerService.selectVehicleByVehicleNumber(voEngineer.getVehicleNumber());
            if (dbEngineer!=null){//原本数据库存在
                engineerService.deleteEngineer(dbEngineer.getId());//需要删除原数据
            }
        }
        VoDriver voDriver = voEngineer.getDriver();
        Driver driver = new Driver();
        driver.setUuid(UUID.randomUUID().toString());//生成driver的uuid
        driver.setName(voDriver.getName());
        driver.setPhone(voDriver.getPhone());
        Engineer engineer = new Engineer();
        engineer.setUuid(voEngineer.getUuid());
        engineer.setType("车辆");
        engineer.setInputTime(new Date());
        engineer.setDeviceId(voEngineer.getDeviceId());
        engineer.setModelId(voEngineer.getModel().getId());
        engineer.setVehicleNumber(voEngineer.getVehicleNumber());
        engineer.setPlateType(voEngineer.getPlateType());
        //HttpStatus statusCode = devService.insertDev(engineer.getDeviceId(), engineer.getVehicleNumber());
        Boolean result = engineerService.insertEngineerAndDriver(driver, engineer);
        return new ResultResponse()
                //.setCode(statusCode.value())
                .setMessage("新增车辆 "+voEngineer.getVehicleNumber())
                .setData(result);//返回新增的结果
    }

    @CrudLog("新增机械及其驾驶员信息")
    @PostMapping("/insertMachine")
    public ResultResponse insertMachine(@RequestBody VoEngineer voEngineer){
//        System.out.println("voEngineer = " + voEngineer);
        if (voEngineer.getEngineNumber()!=null && (!voEngineer.getEngineNumber().equals(""))){
            //车牌号不为空需要判断数据库中原本是否存在该车辆
            Engineer dbEngineer = engineerService.selectMachineByEngineerNumber(voEngineer.getEngineNumber());
            if (dbEngineer!=null){//原本数据库存在
                engineerService.deleteEngineer(dbEngineer.getId());//需要删除原数据
            }
        }
        VoDriver voDriver = voEngineer.getDriver();
        Driver driver = new Driver();
        driver.setUuid(UUID.randomUUID().toString());//生成driver的uuid
        driver.setName(voDriver.getName());
        driver.setPhone(voDriver.getPhone());
        Engineer engineer = new Engineer();
        engineer.setUuid(voEngineer.getUuid());
        engineer.setType("机械");
        engineer.setInputTime(new Date());
        engineer.setDeviceId(voEngineer.getDeviceId());
        engineer.setModelId(voEngineer.getModel().getId());
        engineer.setMachineNumber(voEngineer.getMachineNumber());
        engineer.setEngineNumber(voEngineer.getEngineNumber());
        //HttpStatus statusCode = devService.insertDev(engineer.getDeviceId(), engineer.getEngineNumber());
        Boolean result = engineerService.insertEngineerAndDriver(driver, engineer);
        return new ResultResponse()
                //.setCode(statusCode.value())
                .setMessage("新增机械 "+voEngineer.getEngineNumber())
                .setData(result);//返回新增结果
    }

    @CrudLog("删除车辆")
    @GetMapping("/deleteVehicle")
    public ResultResponse deleteVehicle(@RequestParam("id")Integer id){
//        System.out.println("id = " + id);
        Boolean result = engineerService.deleteEngineer(id);
        return new ResultResponse()
                .setData(result)
                .setMessage("删除车辆"+id);
    }

    @CrudLog("删除机械")
    @GetMapping("/deleteMachine")
    public ResultResponse deleteMachine(@RequestParam("id")Integer id){
//        System.out.println("id = " + id);
        Boolean result = engineerService.deleteEngineer(id);
        return new ResultResponse()
                .setData(result)
                .setMessage("删除机械 "+id);
    }

    @CrudLog("修改车辆及其驾驶员信息")
    @PostMapping("/updateVehicle")
    public ResultResponse updateVehicle(@RequestBody VoEngineer voEngineer){
//        System.out.println("voEngineer = " + voEngineer);
        Boolean result = false;
        Engineer dbEngineer = engineerService.selectEngineerById(voEngineer.getId());//根据id查询是否存在该记录
        //修改前先查询数据库中是否存在该记录
        if (dbEngineer!=null){ //（如果存在则执行修改操作）：会修改engineer和driver表中的信息
            Engineer engineer = new Engineer();
            engineer.setId(voEngineer.getId());//engineer表的id字段
            engineer.setVehicleNumber(voEngineer.getVehicleNumber());//engineer表的vehicleNumber
            engineer.setPlateType(voEngineer.getPlateType());//engineer表的plateType
            engineer.setModelId(voEngineer.getModel().getId());//engineer表的modelId字段（修改所属类型）
            Driver driver = new Driver();
            driver.setId(voEngineer.getDriver().getId());//driver表的id字段
            driver.setName(voEngineer.getDriver().getName());//driver表的name字段
            driver.setPhone(voEngineer.getDriver().getPhone());//driver表的phone字段
            result = engineerService.updateEngineerAndDriver(driver, engineer);
        }
        return new ResultResponse()
                .setMessage("修改车辆 "+voEngineer.getVehicleNumber())
                .setData(result);
    }

    @CrudLog("修改机械及其驾驶员信息")
    @PostMapping("/updateMachine")
    public ResultResponse updateMachine(@RequestBody VoEngineer voEngineer){
//        System.out.println("voEngineer = " + voEngineer);
        Boolean result = false;
        Engineer dbEngineer = engineerService.selectEngineerById(voEngineer.getId());//根据id查询是否存在该记录
        //修改前先查询数据库中是否存在该记录
        if (dbEngineer!=null){ //（如果存在则执行修改操作）：会修改engineer和driver表中的信息
            Engineer engineer = new Engineer();
            engineer.setId(voEngineer.getId());//engineer表的id字段
            engineer.setModelId(voEngineer.getModel().getId());//engineer表的modelId字段（修改所属类型）
            engineer.setMachineNumber(voEngineer.getMachineNumber());//engineer表的vehicleNumber
            engineer.setEngineNumber(voEngineer.getEngineNumber());//engineer表的plateType
            Driver driver = new Driver();
            driver.setId(voEngineer.getDriver().getId());//driver表的id字段
            driver.setName(voEngineer.getDriver().getName());//driver表的name字段
            driver.setPhone(voEngineer.getDriver().getPhone());//driver表的phone字段
            result = engineerService.updateEngineerAndDriver(driver, engineer);
        }
        return new ResultResponse()
                .setMessage("修改机械 "+voEngineer.getEngineNumber())
                .setData(result);
    }

    @GetMapping("/selectListByType")
    public ResultResponse selectListByType(@RequestParam("type")String type,
                                           Integer currentPage,
                                           Integer pageSize){
//        System.out.println("type = " + type + ", currentPage = " + currentPage + ", pageSize = " + pageSize);
        List<Engineer> engineerList = engineerService.selectListByType(type, currentPage, pageSize);
        Engineer engineer = new Engineer();
        engineer.setType(type);
        Long totalSize = engineerService.selectTotalSize(engineer);//查询出该type下的记录数
        VoEngineerList voEngineerList = new VoEngineerList();
        voEngineerList.setItems(engineerList);
        voEngineerList.setTotalSize(totalSize);
        voEngineerList.setPageSize(pageSize);
        voEngineerList.setCurrentPage(currentPage);
        return new ResultResponse()
                .setData(voEngineerList)
                .setMessage("查询 "+type+" 列表");
    }

}
