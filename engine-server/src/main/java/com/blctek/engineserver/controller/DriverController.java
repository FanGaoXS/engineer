package com.blctek.engineserver.controller;

import com.blctek.commonserver.response.ResultResponse;
import com.blctek.engineserver.anno.CrudLog;
import com.blctek.engineserver.pojo.Driver;
import com.blctek.engineserver.service.DriverService;
import com.blctek.engineserver.vo.VoDriver;
import com.blctek.engineserver.vo.VoDriverList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/14/19:40
 * @Description: controller层：对驾驶员信息的修改和查询
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/driver")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @CrudLog("修改驾驶员")
    @PostMapping("/updateDriver")
    public ResultResponse updateDriver(@RequestBody VoDriver voDriver){
//        System.out.println("voDriver = " + voDriver);
        Driver driver = new Driver();
        driver.setId(voDriver.getId());
        driver.setUuid(UUID.randomUUID().toString());
        driver.setName(voDriver.getName());
        driver.setPhone(voDriver.getPhone());
        Boolean result = driverService.updateDriver(driver);
        return new ResultResponse()
                .setMessage("修改驾驶员"+voDriver.getName())
                .setData(result);
    }

    //@CrudLog("查询驾驶员列表")
    @GetMapping("/selectList")
    public ResultResponse selectDriver(Integer currentPage,Integer pageSize){
//        System.out.println("currentPage = " + currentPage + ", pageSize = " + pageSize);
        List<Driver> driverList = driverService.selectList(currentPage, pageSize);
        Long totalSize = driverService.selectTotalSize(new Driver());
        VoDriverList voDriverList = new VoDriverList(driverList);//将driverList转化为voDriverList
        voDriverList.setCurrentPage(currentPage);
        voDriverList.setPageSize(pageSize);
        voDriverList.setTotalSize(totalSize);
        return new ResultResponse()
                .setMessage("查询驾驶员列表")
                .setData(voDriverList);
    }
}
