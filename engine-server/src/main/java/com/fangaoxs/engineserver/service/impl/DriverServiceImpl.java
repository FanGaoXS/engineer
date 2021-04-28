package com.fangaoxs.engineserver.service.impl;

import com.fangaoxs.engineserver.mapper.DriverMapper;
import com.fangaoxs.engineserver.pojo.Driver;
import com.fangaoxs.engineserver.service.DriverService;
import com.fangaoxs.engineserver.vo.VoDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<VoDriver> selectList(Integer currentPage, Integer pageSize) {
        Driver driver = new Driver();
        driver.setPageSize(pageSize);
        driver.setCurrentPage(currentPage);
        List<Driver> driverList = driverMapper.selectList(driver);
        ArrayList<VoDriver> voDriverList = new ArrayList<>();
        driverList.forEach(driver1 -> voDriverList.add(new VoDriver(driver1)));
        return voDriverList;
    }

    @Override
    public Long selectTotalSize(Driver driver) {
        return driverMapper.count(driver);
    }
}
