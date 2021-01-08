package com.blctek.authserver.utils;


import com.blctek.ldapserver.pojo.Developer;
import org.springframework.web.client.RestTemplate;


/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/01/04/12:33
 * @Description:    向其他后端发起http请求的工具类
 */
public class HttpUtils {

    // ldap服务器
    public static String LOCAL_LDAP_BASE_URL = "http://localhost:8091";

    // car服务器
    public static String LOCAL_CAR_BASE_URL = "http://localhost:8092";

    /**
     * 利用http请求向保存有ldap的开发人员的服务器发起请求
     * @param username  用户名
     * @param password  密码
     * @return  查询到的Developer对象
     */
    public static Developer ldapDeveloperAuth(String username, String password){
        RestTemplate restTemplate = new RestTemplate();
        // http请求的地址+参数
        String url = LOCAL_LDAP_BASE_URL+"/develop/developAuth/"+username+"/"+password;
        return restTemplate.getForObject(url, Developer.class);
    }
}
