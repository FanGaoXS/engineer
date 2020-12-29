package com.blctek.carserver.mapper;

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
 * @Date: 2020/12/25/16:46
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MachineMapperTest {
    @Autowired
    MachineMapper machineMapper;
    @Test
    public void selectAllMachine() {
        List<Machine> machineList = machineMapper.selectAllMachineAndCarAndDriver();
        machineList.forEach(machine -> {
            System.out.println(machine);
        });
    }

    @Test
    public void insertMachine() {
        Machine machine = new Machine();
        machine.setCarId(5);
        machineMapper.insertMachine(machine);
        System.out.println(machine.getMachineId());
    }

    @Test
    public void updateMachine() {
        Machine machine = new Machine();
        machine.setMachineId(8);
        machine.setEngineNumber("100000000000001");
        int result = machineMapper.updateMachine(machine);
        System.out.println(result);
    }
}