package com.blctek.engineserver.service;

import com.blctek.engineserver.pojo.Driver;
import com.blctek.engineserver.pojo.Engineer;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/09/16:40
 * @Description:    对engineer表的具体业务
 */
@Service
public interface EngineerService {

    /**
     * 新增一条工程用具记录（机械或车辆）以及驾驶员信息
     * @param engineer              工程用具对象
     * @param driver                驾驶员信息对象
     * @return                      true成功，false失败
     */
    Boolean insertEngineerAndDriver(Driver driver,Engineer engineer);

    /**
     * 根据id删除一条工程用具记录
     * @param id                    工程用具编号
     * @return                      true成功，false失败
     */
    Boolean deleteEngineer(Integer id);

    /**
     * 根据id修改一条工程用具记录
     * @param engineer              工程用具对象
     * @return                      true成功，false失败
     */
    Boolean updateEngineer(Engineer engineer);

    /**
     * 根据类型（机械或车辆）查询工程用具列表（可分页）
     * @param type                  类型（机械或车辆）
     * @param currentPage           当前页
     * @param pageSize              每页记录数
     * @return                      工程用具集合
     */
    List<Engineer> selectListByType(String type,
                                    Integer currentPage,
                                    Integer pageSize);

    /**
     * 根据车牌号来查询数据库中是否存在该车辆
     * @param vehicleNumber         车牌号
     * @return                      存在返回该车辆的编号，否则返回null
     */
    Integer isExistVehicle(String vehicleNumber);

    /**
     * 根据机械的引擎编号查询数据库中是否存在该机械
     * @param engineNumber          引擎编号
     * @return                      存在返回该机械的编号，否则返回null
     */
    Integer isExistMachine(String engineNumber);

    /**
     * 查询列表的记录数
     * @param engineer  工程用具对象（可包含条件或分页）
     * @return  记录数
     */
    Long selectTotalSize(Engineer engineer);
}
