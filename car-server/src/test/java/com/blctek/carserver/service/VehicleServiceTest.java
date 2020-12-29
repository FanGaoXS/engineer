package com.blctek.carserver.service;

import com.blctek.carserver.pojo.Car;
import com.blctek.carserver.pojo.Driver;
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
 * @Date: 2020/12/25/17:55
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class VehicleServiceTest {
    @Autowired
    private VehicleService vehicleService;
    @Test
    public void findAllVehicle() {
        List<Vehicle> vehicleList = vehicleService.findAllVehicle();
        vehicleList.forEach(vehicle -> {
            System.out.println(vehicle);
        });
    }

    @Test
    public void addVehicle() {
        Car car = new Car();
        car.setType("车辆");
        car.setChipId("10001");
        Driver driver = new Driver();
        driver.setDriverName("吴青珂");
        driver.setDriverPhone("13594510001");
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleModel("皮卡车");
        vehicle.setPlateType("汽油车");
        vehicle.setPlateNumber("川A2987");
        Vehicle vehicleDB = vehicleService.addVehicle(car, driver, vehicle);
        System.out.println(vehicleDB.getVehicleId());
    }
}