package com.blctek.userserver.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/02/13:46
 * @Description: 用户表的pojo
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User implements Serializable {
    /**
     * 用户自增主键
     */
    private Integer id;

    /**
     * 用户唯一标识
     */
    private String uuid;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    private static final long serialVersionUID = 1L;
}
