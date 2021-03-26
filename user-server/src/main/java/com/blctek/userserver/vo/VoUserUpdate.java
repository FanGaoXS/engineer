package com.blctek.userserver.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/26/11:32
 * @Description:
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VoUserUpdate implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String phone;

}
