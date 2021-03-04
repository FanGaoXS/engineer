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
 * @Description: 与数据库user表交互的用户对象（与表头一一对应）
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

    /**
     * 姓名
     */
    private String name;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 联系方式
     */
    private String phone;

    private static final long serialVersionUID = 1L;
}
