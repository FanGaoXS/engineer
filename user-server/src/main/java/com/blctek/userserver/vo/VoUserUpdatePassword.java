package com.blctek.userserver.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/26/14:49
 * @Description:
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class VoUserUpdatePassword implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String oldPassword;

    private String newPassword;
}
