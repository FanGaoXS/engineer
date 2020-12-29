package com.blctek.carserver.pojo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * vehicle
 * @author 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Vehicle {
    /**
     * 机械编号
     */
    private Integer vehicleId;

    /**
     * 对应的工程车辆编号
     */
    private Integer carId;

    /**
     * 车辆的车牌号
     */
    private String plateNumber;

    /**
     * 车辆的车牌类型（汽油车，新能源）
     */
    private String plateType;

    /**
     * 车辆的类型（皮卡、轻卡...）
     */
    private String vehicleModel;

    private Car car;

    private Driver driver;

}