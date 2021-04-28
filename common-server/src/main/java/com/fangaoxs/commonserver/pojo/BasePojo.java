package com.fangaoxs.commonserver.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/07/19:07
 * @Description: pojo的base，包含分页功能（当前页和每页记录数）
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BasePojo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 当前页（分页查询）
     */
    private Integer currentPage;

    /**
     * 每页记录数（分页查询）
     */
    private Integer pageSize;

}
