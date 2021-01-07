package com.blctek.authserver.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/01/07/16:41
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Developer {

    /*
     *   common name：普通名字
     * */
    private String cn;

    /*
     *   surname：别名
     * */
    private String sn;

    /*
     *   登录用户名
     * */
    private String uid;

    /*
     *   登录密码
     * */
    private String password;


}
