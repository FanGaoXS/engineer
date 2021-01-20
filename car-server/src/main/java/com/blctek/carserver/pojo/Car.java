package com.blctek.carserver.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * car 所属工程用具表
 * @author 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Car {
    /**
     * 工程车辆编号
     */
    private Integer carId;

    /**
     * 类型（车辆或者机械）
     */
    private String type;

    /**
     * 驾驶员编号
     */
    private Integer driverId;

    /**
     * 芯片编号
     */
    private String chipId;

    /**
     * 车辆图片路径
     */
    private String imagePath;

    private Driver driver;

}