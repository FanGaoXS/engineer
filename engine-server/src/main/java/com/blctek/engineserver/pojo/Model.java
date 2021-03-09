package com.blctek.engineserver.pojo;


import com.blctek.commonserver.pojo.BasePojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * model
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Model extends BasePojo {
    /**
     * 自增主键
     */
    private Integer id;

    /**
     * 随机唯一标识
     */
    private String uuid;

    /**
     * 类型名
     */
    private String name;

    /**
     * 所属工程用具（车辆或类型）
     */
    private String type;

    /**
     * 类型描述
     */
    private String description;

}