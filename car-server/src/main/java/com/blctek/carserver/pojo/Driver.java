package com.blctek.carserver.pojo;

import java.io.Serializable;

import com.blctek.commonserver.pojo.BasePojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * driver 驾驶员信息表
 * @author 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Driver extends BasePojo {
    /**
     * 驾驶员编号
     */
    private Integer id;

    /**
     * 驾驶员姓名
     */
    private String driverName;

    /**
     * 驾驶电话号码
     */
    private String driverPhone;

}