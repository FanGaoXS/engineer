package com.blctek.carserver.service;

import com.blctek.carserver.pojo.Car;
import com.blctek.carserver.pojo.Driver;
import com.blctek.carserver.pojo.Machine;
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
 * @Date: 2020/12/25/18:13
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MachineServiceTest {
    @Autowired
    private MachineService machineService;
    @Test
    public void findAllMachine() {
        List<Machine> machineList = machineService.findAllMachine();
        machineList.forEach(machine -> {
            System.out.println(machine);
        });
    }

    @Test
    public void addMachine() {
        Car car = new Car();
        car.setType("机械");
        car.setChipId("10003");
        Driver driver = new Driver();
        driver.setDriverName("赵天珩");
        driver.setDriverPhone("12486411233");
        Machine machine = new Machine();
        machine.setMachineNumber("狂风199");
        machine.setMachineModel("压路机");
        machine.setEngineNumber("461564561586415");
        Machine machineDB = machineService.addMachine(car, driver, machine);
        System.out.println(machineDB.getMachineId());
    }
}