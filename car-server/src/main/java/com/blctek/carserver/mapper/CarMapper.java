package com.blctek.carserver.mapper;

import com.blctek.carserver.pojo.Car;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2020/12/25/16:06
 * @Description: 对car表的crud
 */
@Mapper
@Repository
public interface CarMapper {

    /**
     *  查询所有工程车辆以及它的驾驶员信息
     * @return 工程车辆以及它的驾驶员集合
     */
    List<Car> selectAllCarAndDriver();

    /**
     *  新增工程车辆信息
     * @param car 工程车辆对象
     * @return 新增的工程车辆数量
     */
    int insertCar(Car car);

    /**
     *  修改工程车辆信息
     * @param car   工程车辆对象
     * @return  修改成功的工程车辆数量
     */
    int updateCar(Car car);

    /**
     *  删除工程车辆信息（根据carId）
     * @param carId
     * @return  删除成功的数量
     */
    int deleteCar(Integer carId);
}
