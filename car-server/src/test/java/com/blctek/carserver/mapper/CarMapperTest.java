package com.blctek.carserver.mapper;

import com.blctek.carserver.pojo.Car;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2020/12/25/16:21
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CarMapperTest {
    @Autowired
    private CarMapper carMapper;
    @Test
    public void selectAllCarAndDriver() {
        List<Car> carList = carMapper.selectAllCarAndDriver();
        carList.forEach(car -> {
            System.out.println(car);
        });
    }

    @Test
    public void insertCar() {
        Car car = new Car();
        car.setType("车辆");
        int i = carMapper.insertCar(car);
        System.out.println(car.getCarId());
    }

    @Test
    public void updateCar() {
        Car car = new Car();
        car.setCarId(26);
        car.setChipId("10001");
        int result = carMapper.updateCar(car);
        System.out.println(result);
    }

    @Test
    public void deleteCar() {
        Car car = new Car();
        car.setCarId(30);
        int result = carMapper.deleteCar(car.getCarId());
        System.out.println(result);
    }
}