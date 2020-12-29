package com.blctek.carserver.pojo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * machine
 * @author 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Machine {
    /**
     * 机械编号
     */
    private Integer machineId;

    /**
     * 对应的工程车辆编号
     */
    private Integer carId;

    /**
     * 机械的型号（轻卡320...）
     */
    private String machineNumber;

    /**
     * 机械的发动机编号
     */
    private String engineNumber;

    /**
     * 机械的类型（压路机、装载机...）
     */
    private String machineModel;

    private Car car;

    private Driver driver;

}