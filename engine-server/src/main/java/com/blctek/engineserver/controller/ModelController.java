package com.blctek.engineserver.controller;

import com.blctek.commonserver.response.ResultResponse;
import com.blctek.engineserver.anno.CrudLog;
import com.blctek.engineserver.pojo.Model;
import com.blctek.engineserver.service.ModelService;
import com.blctek.engineserver.vo.VoDriverList;
import com.blctek.engineserver.vo.VoModel;
import com.blctek.engineserver.vo.VoModelList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/14/19:40
 * @Description:    controller层：对类型的新增、修改、删除、查询
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/model")
public class ModelController {
    @Autowired
    private ModelService modelService;

    @CrudLog("新增类型")
    @PostMapping("/insertModel")
    public ResultResponse insertModel(@RequestBody VoModel voModel){
//        System.out.println("voModel = " + voModel);
        Model model = new Model();
        model.setUuid(UUID.randomUUID().toString());    //model表中UUID字段
        model.setName(voModel.getName());               //model表中name字段
        model.setType(voModel.getType());               //model表中type字段
        model.setDescription(voModel.getDescription()); //model表中description字段
        Boolean result = modelService.insertModel(model);
        return new ResultResponse()
                .setData(result)
                .setMessage("新增类型"+voModel.getName());
    }

    @CrudLog("删除类型")
    @GetMapping("/deleteModel")
    public ResultResponse deleteModel(@RequestParam("id")Integer id) {
//        System.out.println("id = " + id);
        Boolean result = modelService.deleteModel(id);
        return new ResultResponse()
                .setData(result)
                .setMessage("删除编号为["+id+"]类型");
    }

    @CrudLog("修改类型")
    @PostMapping("/updateModel")
    public ResultResponse updateModel(@RequestBody VoModel voModel){
//        System.out.println("voModel = " + voModel);
        Model model = new Model();
        model.setId(voModel.getId());                       //model表中id主键
        model.setName(voModel.getName());                   //model表中的name字段
        model.setType(voModel.getType());                   //model表中的type字段
        model.setDescription(voModel.getDescription());     //model表中的description字段
        Boolean result = modelService.updateModel(model);
        return new ResultResponse()
                .setData(result)
                .setMessage("修改类型"+voModel.getName());
    }

    //@CrudLog("查询车辆的类型")
    @GetMapping("/selectListByVehicle")
    public ResultResponse selectListByVehicle(Integer currentPage,Integer pageSize){
//        System.out.println("currentPage = " + currentPage + ", pageSize = " + pageSize);
        String type = "车辆";

        Model model = new Model();
        model.setType(type);
        model.setCurrentPage(currentPage);
        model.setPageSize(pageSize);
        Long totalSize = modelService.selectTotalSize(model);//查询记录数

        List<Model> modelList = modelService.selectModelByType(type, currentPage, pageSize);//查询list

        VoModelList voModelList = new VoModelList(modelList);
        voModelList.setCurrentPage(currentPage);
        voModelList.setPageSize(pageSize);
        voModelList.setTotalSize(totalSize);
        return new ResultResponse()
                .setData(voModelList)
                .setMessage("查询车辆的类型");

    }

    //@CrudLog("查询机械的类型")
    @GetMapping("/selectListByMachine")
    public ResultResponse selectListByMachine(Integer currentPage,Integer pageSize){
//        System.out.println("currentPage = " + currentPage + ", pageSize = " + pageSize);
        String type = "机械";

        Model model = new Model();
        model.setType(type);
        model.setCurrentPage(currentPage);
        model.setPageSize(pageSize);
        Long totalSize = modelService.selectTotalSize(model);//查询记录数

        List<Model> modelList = modelService.selectModelByType(type, currentPage, pageSize);//查询list

        VoModelList voModelList = new VoModelList(modelList);
        voModelList.setCurrentPage(currentPage);
        voModelList.setPageSize(pageSize);
        voModelList.setTotalSize(totalSize);

        return new ResultResponse()
                .setData(voModelList)
                .setMessage("查询机械的类型");
    }

}