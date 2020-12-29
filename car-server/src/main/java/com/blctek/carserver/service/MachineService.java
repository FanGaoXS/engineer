package com.blctek.carserver.service;

import com.blctek.carserver.pojo.Car;
import com.blctek.carserver.pojo.Driver;
import com.blctek.carserver.pojo.Machine;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2020/12/25/18:08
 * @Description:
 */
@Service
public interface MachineService {

    /**
     *  查询所有机械
     * @return 机械、工程用具、驾驶员信息
     */
    List<Machine> findAllMachine();

    /**
     *  新增机械
     * @param car
     * @param driver
     * @param machine
     * @return 新增成功后的机械信息对象
     */
    Machine addMachine(Car car, Driver driver, Machine machine);

    /**
     *  修改机械和驾驶员的相关信息
     * @param machine   机械对象
     * @param driver    驾驶员对象
     * @return  true成功，false失败
     */
    Boolean modifyMachineAndDriverInfo(Machine machine,Driver driver);

    /**
     *  删除机械信息
     * @param machineId 机械编号（删除machine表中行）
     * @param carId 所属工程车辆编号（删除car表中行）
     * @param driverId 驾驶员编号编号（删除driver表中行）
     * @return  true成功，false失败
     */
    Boolean removeMachine(Integer machineId,
                          Integer carId,
                          Integer driverId);
}
