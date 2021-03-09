package com.blctek.engineserver.service;

import com.blctek.engineserver.pojo.Driver;
import com.blctek.engineserver.pojo.Engineer;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/09/16:40
 * @Description:
 */
@Service
public interface EngineerService {

    /**
     * 新增一条车辆记录（同时还会新增一条驾驶员记录）
     * @param engineer
     * @return  新增成功的对象（否则返回null）
     */
    Engineer insertVehicleAndDriver(Engineer engineer, Driver driver);

    /**
     * 新增一条机械记录（同时还会新增一条驾驶员记录）
     * @param engineer
     * @return  新增成功的对象（否则返回null）
     */
    Engineer insertMachineAndDriver(Engineer engineer, Driver driver);
}
