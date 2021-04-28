package com.fangaoxs.engineserver.controller;

import com.fangaoxs.commonserver.response.ResultResponse;
import com.fangaoxs.commonserver.vo.VoList;
import com.fangaoxs.engineserver.anno.CrudLog;
import com.fangaoxs.engineserver.pojo.Driver;
import com.fangaoxs.engineserver.service.DriverService;
import com.fangaoxs.engineserver.vo.VoDriver;
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
    public ResultResponse selectDriver(@RequestParam(required = false) Integer currentPage,
                                       @RequestParam(required = false) Integer pageSize){
//        System.out.println("currentPage = " + currentPage + ", pageSize = " + pageSize);
        List<VoDriver> voDriverList = driverService.selectList(currentPage, pageSize);
        Long totalSize = driverService.selectTotalSize(new Driver());
        VoList<VoDriver> voList = new VoList<>();
        voList.setItems(voDriverList);
        voList.setTotalSize(totalSize);
        voList.setCurrentPage(currentPage);
        voList.setPageSize(pageSize);
        return new ResultResponse()
                .setMessage("查询驾驶员列表")
                .setData(voList);
    }
}
