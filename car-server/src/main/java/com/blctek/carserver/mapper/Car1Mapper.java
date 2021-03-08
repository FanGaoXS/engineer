package com.blctek.carserver.mapper;

import com.blctek.carserver.pojo.Car;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/08/18:10
 * @Description:
 */
@Mapper
@Repository
public interface Car1Mapper {

    /**
     * 增加一条记录
     *
     * @param car 车辆对象
     * @return 增加成功的记录数
     */
    Integer insertOne(Car car);

    /**
     * 删除一条记录
     *
     * @param car 车辆对象
     * @return 删除成功的记录数
     */
    Integer deleteOne(Car car);

    /**
     * 修改一条记录
     *
     * @param car 车辆对象
     * @return 修改成功的记录数
     */
    Integer updateOne(Car car);

    /**
     * 查询所有集合（可分页，可多条件，可单条件）以及附属的驾驶员信息
     *
     * @param car 车辆对象
     * @return car集合
     */
    List<Car> selectList(Car car);

    /**
     * 查询一条car记录（可多条件，可单条件）以及附属的驾驶员信息
     *
     * @param car 车辆对象
     * @return 一条车辆对象
     */
    Car selectOne(Car car);

    /**
     * 查询记录数（可条件查询）
     *
     * @param car 车辆对象
     * @return 记录数
     */
    Long count(Car car);

}
