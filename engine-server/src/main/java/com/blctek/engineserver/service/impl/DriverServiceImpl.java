package com.blctek.engineserver.service.impl;

import com.blctek.engineserver.mapper.DriverMapper;
import com.blctek.engineserver.pojo.Driver;
import com.blctek.engineserver.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/12/11:58
 * @Description:
 */
@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverMapper driverMapper;

    @Override
    public Boolean updateDriver(Driver driver) {
        return driverMapper.updateOne(driver)>0;
    }

    @Override
    public List<Driver> selectList(Integer currentPage, Integer pageSize) {
        Driver driver = new Driver();
        driver.setPageSize(pageSize);
        driver.setCurrentPage(currentPage);
        return driverMapper.selectList(driver);
    }

    @Override
    public Long selectTotalSize(Driver driver) {
        return driverMapper.count(driver);
    }
}
