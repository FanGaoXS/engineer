package com.blctek.userserver.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/29/17:15
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Salt {

    private Integer id;

    private String username;

    private Integer value;

}
