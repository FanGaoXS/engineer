package com.fangaoxs.userserver.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/02/13:05
 * @Description: 与前端视图直接交互的对象（登录页面）
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class VoUserLogin implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;

    private String password;

    /**
     * token有效期（单位：天）
     */
    private Integer validityDay;
}
