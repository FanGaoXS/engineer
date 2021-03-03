package com.blctek.userserver.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/03/18:16
 * @Description: 前端交互的用户信息
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VoUserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String avatar;

    private String phone;
}
