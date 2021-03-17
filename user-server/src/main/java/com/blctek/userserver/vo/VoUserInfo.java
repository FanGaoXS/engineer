package com.blctek.userserver.vo;

import com.blctek.userserver.pojo.User;
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

    /**
     * 直接将从数据库中查出的pojo对象赋值给VoUserInfo
     * 将数据库中字段直接赋值给视图中需要的字段
     * @param user  数据库查询出来的用户对象
     */
    public VoUserInfo(User user) {
        this.name = user.getName();
        this.avatar = user.getAvatar();
        this.phone = user.getPhone();
    }
}
