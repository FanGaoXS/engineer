package com.blctek.carserver.mapper;

import com.blctek.carserver.pojo.Model;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/01/20/12:55
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ModelMapperTest {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    Model model;

    @Test
    public void insertModel() {
        model.setModelName("测试");
        model.setModelDescription("测试");
        modelMapper.insertModel(model);
        System.out.println(model);
        modelMapper.deleteModelById(model.getId());
    }

    @Test
    public void deleteModelById() {
    }

    @Test
    public void updateModel() {
        model.setId(11);
        model.setModelName("四桥车");
        modelMapper.updateModel(model);
        System.out.println(model);
    }

    @Test
    public void selectAllModel() {
        List<Model> modelList = modelMapper.selectAllModel();
        modelList.forEach(model->{
            System.out.println(model);
        });
    }

    @Test
    public void selectModelByCondition() {
        model.setModelBelong("车辆");
        model.setId(6);
        List<Model> modelList = modelMapper.selectModelByCondition(model);
        modelList.forEach(model1 -> {
            System.out.println(model1);
        });
    }
}