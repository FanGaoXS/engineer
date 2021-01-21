package com.blctek.carserver.service;

import com.blctek.carserver.pojo.Model;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/01/20/13:11
 * @Description:
 */
@Service
public interface ModelService {

    /**
     *  列出所有类型
     * @return  类型集合
     */
    List<Model> selectAllModel();

    /**
     *  查出具体所属类型（车辆的所有类型或机械的所有类型）
     * @return  类型集合
     */
    List<Model> selectModelByBelong(String modelBelong);

    /**
     *  新增类型
     * @return
     */
    Boolean insertModel(Model model);

    /**
     * 删除类型
     * @return
     */
    Boolean deleteModel(Integer id);

    /**
     * 修改类型
     * @param model
     * @return
     */
    Boolean updateModel(Model model);
}
