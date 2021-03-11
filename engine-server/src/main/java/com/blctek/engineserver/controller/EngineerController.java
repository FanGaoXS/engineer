package com.blctek.engineserver.controller;

import com.blctek.commonserver.response.ResultResponse;
import com.blctek.engineserver.pojo.Driver;
import com.blctek.engineserver.pojo.Engineer;
import com.blctek.engineserver.service.DevService;
import com.blctek.engineserver.service.EngineerService;
import com.blctek.engineserver.vo.VoDriver;
import com.blctek.engineserver.vo.VoEngineer;
import com.blctek.engineserver.vo.VoEngineerList;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/10/14:16
 * @Description:
 */
@RestController
@RequestMapping("/engineer")
@CrossOrigin("*")
public class EngineerController {

    @Autowired
    private EngineerService engineerService;
    @Autowired
    private DevService devService;

    @PostMapping("/insertVehicle")
    public ResultResponse insertVehicle(@RequestBody VoEngineer voEngineer){

        System.out.println(voEngineer);

        if (voEngineer.getVehicleNumber()!=null && (!voEngineer.getVehicleNumber().equals(""))){
            //车牌号不为空需要判断数据库中原本是否存在该车辆
            Integer existVehicleId = engineerService.isExistVehicle(voEngineer.getVehicleNumber());
            if (existVehicleId!=null){//原本数据库存在
                engineerService.deleteEngineer(existVehicleId);//需要删除原数据
            }
        }

        VoDriver voDriver = voEngineer.getDriver();

        Driver driver = new Driver();
        driver.setUuid(UUID.randomUUID().toString());//生成driver的uuid
        driver.setName(voDriver.getName());
        driver.setPhone(voDriver.getPhone());

        Engineer engineer = new Engineer();
        engineer.setUuid(voEngineer.getUuid());
        engineer.setType(voEngineer.getType());
        engineer.setDeviceId(voEngineer.getDeviceId());
        engineer.setModelId(voEngineer.getModel().getId());
        engineer.setVehicleNumber(voEngineer.getVehicleNumber());
        engineer.setPlateType(voEngineer.getPlateType());

        //HttpStatus statusCode = devService.insertDev(engineer.getDeviceId(), engineer.getVehicleNumber());
        Boolean result = engineerService.insertEngineerAndDriver(driver, engineer);

        return new ResultResponse()
                //.setCode(statusCode.value())
                .setMessage("新增工程用具")
                .setData(result);//返回新增的结果
    }

    @PostMapping("/insertMachine")
    public ResultResponse insertMachine(@RequestBody VoEngineer voEngineer){

        System.out.println(voEngineer);

        if (voEngineer.getEngineNumber()!=null && (!voEngineer.getEngineNumber().equals(""))){
            //车牌号不为空需要判断数据库中原本是否存在该车辆
            Integer existVehicleId = engineerService.isExistMachine(voEngineer.getEngineNumber());
            if (existVehicleId!=null){//原本数据库存在
                engineerService.deleteEngineer(existVehicleId);//需要删除原数据
            }
        }

        VoDriver voDriver = voEngineer.getDriver();

        Driver driver = new Driver();
        driver.setUuid(UUID.randomUUID().toString());//生成driver的uuid
        driver.setName(voDriver.getName());
        driver.setPhone(voDriver.getPhone());

        Engineer engineer = new Engineer();
        engineer.setUuid(voEngineer.getUuid());
        engineer.setType(voEngineer.getType());
        engineer.setDeviceId(voEngineer.getDeviceId());
        engineer.setModelId(voEngineer.getModel().getId());
        engineer.setMachineNumber(voEngineer.getMachineNumber());
        engineer.setEngineNumber(voEngineer.getEngineNumber());

        //HttpStatus statusCode = devService.insertDev(engineer.getDeviceId(), engineer.getEngineNumber());
        Boolean result = engineerService.insertEngineerAndDriver(driver, engineer);

        return new ResultResponse()
                //.setCode(statusCode.value())
                .setMessage("新增工程用具")
                .setData(result);//返回新增结果
    }

    @GetMapping("/selectListByType")
    public ResultResponse selectListByType(@RequestParam("type")String type,
                                           Integer currentPage,
                                           Integer pageSize){
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
                .setMessage("查询"+type+"列表");
    }

    @PostMapping("/updateVehicle")
    public ResultResponse updateVehicle(){
        return new ResultResponse();
    }

    @PostMapping("/updateMachine")
    public ResultResponse updateMachine(){
        return new ResultResponse();
    }

}
