package com.blctek.engineserver.controller;

import com.blctek.commonserver.response.ResultResponse;
import com.blctek.engineserver.pojo.Engineer;
import com.blctek.engineserver.service.EngineerService;
import com.blctek.engineserver.vo.VoDriver;
import com.blctek.engineserver.vo.VoEngineer;
import com.blctek.engineserver.vo.VoEngineerList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/insertEngineer")
    public ResultResponse insertEngineer(@RequestHeader("X-Client")String clientName,
                                         @RequestBody VoEngineer voEngineer){
        System.out.println(clientName);
        System.out.println(voEngineer);
        return new ResultResponse()
                .setMessage("新增工程用具")
                .setData(null);
    }

    @GetMapping("/selectList")
    public ResultResponse selectList(Engineer engineer){
        //根据传入的engineer对象查询列表并算出该条件下的总记录数
        List<Engineer> engineerList = engineerService.selectList(engineer);
        Long totalSize = engineerService.selectTotalSize(engineer);

        //将engineerList对象转为voEngineerList对象
        VoEngineerList voEngineerList = new VoEngineerList();
        voEngineerList.setItems(engineerList);
        voEngineerList.setCurrentPage(engineer.getCurrentPage());
        voEngineerList.setPageSize(engineer.getPageSize());
        voEngineerList.setTotalSize(totalSize);

        return new ResultResponse()
                .setData(voEngineerList)
                .setMessage("查询工程用具列表");
    }
}
