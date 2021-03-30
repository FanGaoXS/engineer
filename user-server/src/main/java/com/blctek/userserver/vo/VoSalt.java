package com.blctek.userserver.vo;

import com.blctek.userserver.pojo.Salt;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/30/12:12
 * @Description:
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VoSalt implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String username;

    private Integer salt;

    public VoSalt(Salt salt){
        if (salt!=null){
            this.id = salt.getId();
            this.username = salt.getUsername();
            this.salt = salt.getValue();
        }
    }
}
