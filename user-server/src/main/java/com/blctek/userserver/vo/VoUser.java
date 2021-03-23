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
 * @Date: 2021/03/23/16:38
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String uuid;

    private String username;

    private String name;

    private String avatar;

    private String phone;

    private VoRole role;

    public VoUser(User user) {
        this.id = user.getId();
        this.uuid = user.getUuid();
        this.username = user.getUsername();
        this.name = user.getName();
        this.avatar = user.getAvatar();
        this.phone = user.getPhone();
        this.role = new VoRole(user.getRole());
    }
}
