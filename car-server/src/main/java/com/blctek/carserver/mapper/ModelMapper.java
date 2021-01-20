package com.blctek.carserver.mapper;

import com.blctek.carserver.pojo.Model;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/01/20/12:29
 * @Description: 对model类型表进行操作
 */
@Mapper
@Repository
public interface ModelMapper {

    /**
     *  增加新的类型
     * @return  成功增加的记录数
     */
    int insertModel(Model model);

    /**
     *  根据类型编号删除类型
     * @param id    类型编号
     * @return  成功删除类型的记录数
     */
    int deleteModelById(Integer id);

    /**
     *  修改model
     * @param model 类型对象
     * @return  成功修改类型的记录数
     */
    int updateModel(Model model);

    /**
     *  查询所有类型（包括机械或车辆）
     * @return  类型集合
     */
    List<Model> selectAllModel();

    /**
     *  条件查询
     * @param model 类型对象
     * @return  类型集合
     */
    List<Model> selectModelByCondition(Model model);

}
