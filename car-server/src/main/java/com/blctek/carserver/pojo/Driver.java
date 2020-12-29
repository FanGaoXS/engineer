package com.blctek.carserver.pojo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * driver
 * @author 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Driver {
    /**
     * 驾驶员编号
     */
    private Integer driverId;

    /**
     * 驾驶员姓名
     */
    private String driverName;

    /**
     * 驾驶电话号码
     */
    private String driverPhone;

}