package com.blctek.engineserver.service;

import com.blctek.engineserver.pojo.Driver;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/11/16:23
 * @Description:
 */
@Service
public interface DriverService {

    /**
     * 删除一条驾驶员信息
     * @param id    驾驶员编号
     * @return      true成功，false失败
     */
    Boolean deleteDriver(Integer id);

    /**
     * 修改驾驶员信息
     * @param driver    驾驶员对象
     * @return          true成功，false失败
     */
    Boolean updateDriver(Driver driver);

    /**
     * 查询驾驶员列表（可单条件，可多条件，可分页）
     * @param driver    驾驶员对象
     * @return          true成功，false失败
     */
    List<Driver> selectList(Driver driver);

    /**
     * 查询驾驶员列表的记录数（可单条件，可多条件）
     * @param driver    驾驶员对象
     * @return          记录数
     */
    Long selectTotalSize(Driver driver);
}
