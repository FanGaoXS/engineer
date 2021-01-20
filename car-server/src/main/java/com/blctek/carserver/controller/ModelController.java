package com.blctek.carserver.controller;

import com.blctek.carserver.pojo.Model;
import com.blctek.carserver.service.ModelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/01/20/13:16
 * @Description:
 */
@RestController
@RequestMapping("model")
@Slf4j
public class ModelController {
    @Autowired
    ModelService modelService;

    @GetMapping("/modelList")
    public List<Model> selectAllModel(){
        return modelService.selectAllModel();
    }

    @GetMapping("/modelList/" +
            "{modelBelong}")
    public List<Model> selectModelByBelong(@PathVariable("modelBelong")String modelBelong){
        log.info("查询所属为[{}]的类型",modelBelong);
        return modelService.selectModelByBelong(modelBelong);
    }

    /*@GetMapping("/insertModel/" +
            "{modelName}/" +
            "{modelDescription}/" +
            "{modelExampleImage}/" +
            "{modelBelong}")*/
    @GetMapping("/insertModel")
    public Boolean insertModel(Model model){
        log.info("新增类型");
        log.info("类型名->[{}]",model.getModelName());
        log.info("类型描述->[{}]",model.getModelDescription());
        log.info("类型示例图片->[{}]",model.getModelExampleImage());
        log.info("类型所属->[{}]",model.getModelBelong());
        Boolean result = modelService.insertModel(model);
        if (result){
            log.info("新增类型名为[{}]的类型成功",model.getModelName());
            return true;
        }
        log.info("新增类型名为[{}]的类型失败",model.getModelName());
        return false;
    }

    @GetMapping("/deleteModel/" +
            "{id}")
    public Boolean deleteModel(@PathVariable Integer id){
        Boolean result = modelService.deleteModel(id);
        if (result) {
            log.info("删除类型编号为[{}]的类型成功",id);
            return true;
        }
        log.info("删除类型编号为[{}]的类型失败",id);
        return false;
    }
}
