package com.blctek.carserver.mapper;

import com.blctek.carserver.pojo.Vehicle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2020/12/25/16:46
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class VehicleMapperTest {
    @Autowired
    VehicleMapper vehicleMapper;
    @Test
    public void selectAllVehicle() {
        List<Vehicle> vehicleList = vehicleMapper.selectAllVehicleAndCarAndDriver();
        vehicleList.forEach(vehicle -> {
            System.out.println(vehicle);
        });
    }

    @Test
    public void insertVehicle() {
        Vehicle vehicle = new Vehicle();
        vehicle.setCarId(5);
        vehicleMapper.insertVehicle(vehicle);
        System.out.println(vehicle.getVehicleId());
    }

    @Test
    public void updateVehicle() {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId(16);
        vehicle.setPlateNumber("川A.1234B");
        int result = vehicleMapper.updateVehicle(vehicle);
        System.out.println(result);
    }
}