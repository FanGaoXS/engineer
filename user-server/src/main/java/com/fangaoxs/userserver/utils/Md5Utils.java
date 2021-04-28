package com.fangaoxs.userserver.utils;

import org.springframework.util.DigestUtils;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/30/11:15
 * @Description:
 */
public class Md5Utils {

    /**
     * 获得随机盐值
     * @return                  随机盐
     */
    public static Integer randomSalt(){
        return (int)(Math.random()*Integer.MAX_VALUE);
    }

    /**
     * 生成md5：
     *  规则：密码+'|'+随机盐
     * @param password
     * @param randSalt
     * @return                  经过md5算法后的password
     */
    public static String md5Password(String password,Integer randSalt){
        byte[] passwordByte = (password +"|"+ randSalt).getBytes();
        return DigestUtils.md5DigestAsHex(passwordByte);
    }
}
