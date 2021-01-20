package com.blctek.carserver.service.impl;

import com.blctek.carserver.mapper.ModelMapper;
import com.blctek.carserver.pojo.Model;
import com.blctek.carserver.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/01/20/13:22
 * @Description:
 */
@Service
public class ModelServiceImpl implements ModelService {
    @Autowired
    ModelMapper modelMapper;
    @Override
    public List<Model> selectAllModel() {
        return modelMapper.selectAllModel();
    }

    @Override
    public List<Model> selectModelByBelong(String modelBelong) {
        Model model = new Model();
        model.setModelBelong(modelBelong);
        return modelMapper.selectModelByCondition(model);
    }

    @Override
    public Boolean insertModel(Model model) {
        int i = modelMapper.insertModel(model);
        if (i>0) {
            return true;// 增加成功
        }
        return false;// 增加失败
    }

    @Override
    public Boolean deleteModel(Integer id) {
        int i = modelMapper.deleteModelById(id);
        if (i>0) {
            return true;// 删除成功
        }
        return false;// 删除失败
    }
}
