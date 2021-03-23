package com.blctek.userserver.vo;

import com.blctek.userserver.pojo.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/23/12:38
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoRole implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String chineseName;

    private String description;

    public VoRole(Role role) {
        this.id = role.getId();
        this.name = role.getName();
        this.chineseName = role.getChineseName();
        this.description = role.getDescription();
    }
}
