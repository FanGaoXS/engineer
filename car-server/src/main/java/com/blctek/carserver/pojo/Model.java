package com.blctek.carserver.pojo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * model 类型信息表
 * @author
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Model implements Serializable{
    /**
     * 类型自增主键
     */
    private Integer id;

    /**
     * 类型名字
     */
    private String modelName;

    /**
     * 类型的具体描述
     */
    private String modelDescription;

    /**
     * 类型的示例图片的uuid
     */
    private String modelExampleImage;

    /**
     * 类型所属（机械或车辆）
     */
    private String modelBelong;

    private static final long serialVersionUID = 1L;
}