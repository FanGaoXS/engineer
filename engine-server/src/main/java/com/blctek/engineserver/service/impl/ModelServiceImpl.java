package com.blctek.engineserver.service.impl;

import com.blctek.engineserver.mapper.ModelMapper;
import com.blctek.engineserver.pojo.Model;
import com.blctek.engineserver.service.ModelService;
import com.blctek.engineserver.vo.VoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/14/19:23
 * @Description:
 */
@Service
public class ModelServiceImpl implements ModelService {
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public Boolean insertModel(Model model) {
        return modelMapper.insertOne(model)>0;
    }

    @Override
    public Boolean deleteModel(Integer id) {
        Model model = new Model();
        model.setId(id);
        return modelMapper.deleteOne(model)>0;
    }

    @Override
    public Boolean updateModel(Model model) {
        return modelMapper.updateOne(model)>0;
    }

    @Override
    public List<VoModel> selectModelByType(String type, Integer currentPage, Integer pageSize) {
        Model model = new Model();
        model.setType(type);
        model.setCurrentPage(currentPage);
        model.setPageSize(pageSize);
        List<Model> modelList = modelMapper.selectList(model);
        ArrayList<VoModel> voModelList = new ArrayList<>();
        modelList.forEach(model1 -> voModelList.add(new VoModel(model1)));
        return voModelList;
    }

    @Override
    public Long selectTotalSize(Model model) {
        return modelMapper.count(model);
    }
}
