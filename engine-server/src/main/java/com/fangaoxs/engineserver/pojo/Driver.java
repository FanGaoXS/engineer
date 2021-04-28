package com.fangaoxs.engineserver.pojo;


import com.fangaoxs.commonserver.pojo.BasePojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * driver   驾驶员表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Driver extends BasePojo {
    /**
     * 自增主键
     */
    private Integer id;

    /**
     * 随机唯一标识
     */
    private String uuid;

    /**
     * 驾驶员姓名
     */
    private String name;

    /**
     * 驾驶员联系方式
     */
    private String phone;

}
