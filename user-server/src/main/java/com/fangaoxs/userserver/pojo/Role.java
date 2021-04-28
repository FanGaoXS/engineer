package com.fangaoxs.userserver.pojo;

import com.fangaoxs.commonserver.pojo.BasePojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/23/11:23
 * @Description:    与数据库的role表一一对应
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role  extends BasePojo {

    /**
     * 角色自增主键
     */
    private Integer id;

    /**
     * 角色名
     */
    private String name;

    /**
     * 角色中文名
     */
    private String chineseName;

    /**
     * 角色描述
     */
    private String description;

}
