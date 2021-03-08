package com.blctek.carserver.pojo;

import com.blctek.commonserver.pojo.BasePojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * car 所属工程用具表
 * @author 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car extends BasePojo {
    /**
     * 工程车辆编号
     */
    private Integer id;

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

    /**
     * 录入时间
     */
    private Date inputTime;

    /**
     * 附属的驾驶员对象
     */
    private Driver driver;

}