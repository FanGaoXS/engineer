package com.blctek.engineserver.pojo;

import java.util.Date;

import com.blctek.commonserver.pojo.BasePojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * engineer 与工程用具的字段对应
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Engineer extends BasePojo {
    /**
     * 自增主键
     */
    private Integer id;

    /**
     * 随机唯一标识
     */
    private String uuid;

    /**
     * 所属工程用具（车辆或机械）
     */
    private String type;

    /**
     * 设备编号
     */
    private String deviceId;

    /**
     * 所属的驾驶员编号
     */
    private Integer driverId;

    /**
     * 录入系统的时间
     */
    private Date inputTime;

    /**
     * 类型名（130轻卡，油罐车等）
     */
    private Integer modelId;

    /**
     * 车牌号（车辆）
     */
    private String vehicleNumber;

    /**
     * 车牌类型（车辆）：汽油车或新能源
     */
    private String plateType;

    /**
     * 机械型号（机械）
     */
    private String machineNumber;

    /**
     * 发动机编号（机械）
     */
    private String engineNumber;

    /**
     * 附属的驾驶员信息对象
     */
    private Driver driver;

    /**
     * 附属的类型信息对象
     */
    private Model model;

}