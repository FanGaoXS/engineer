package com.blctek.carserver.mapper;

import com.blctek.carserver.pojo.Vehicle;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2020/12/25/16:09
 * @Description: 对vehicle表的crud
 */
@Mapper
@Repository
public interface VehicleMapper {

    /**
     *  查询所有车辆集合
     * @return 车辆以及它的工程信息集合
     */
    List<Vehicle> selectAllVehicleAndCarAndDriver();

    /**
     *  新增车辆
     * @param vehicle 车辆对象
     * @return 新增车辆的数量
     */
    int insertVehicle(Vehicle vehicle);

    /**
     *  修改车辆
     * @param vehicle   车辆对象
     * @return  修改成功的数量
     */
    int updateVehicle(Vehicle vehicle);

    /**
     *  删除车辆（根据vehicleId）
     * @param vehicleId
     * @return  成功删除的数量
     */
    int deleteVehicle(Integer vehicleId);
}
