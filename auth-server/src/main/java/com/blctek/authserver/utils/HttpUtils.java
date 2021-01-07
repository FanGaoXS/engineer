package com.blctek.authserver.utils;

import com.blctek.authserver.pojo.Developer;
import org.springframework.web.client.RestTemplate;


/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/01/04/12:33
 * @Description:
 */
public class HttpUtils {
    //利用http发送GET请求
    public static Developer developerAuth(String username,String password){
        RestTemplate restTemplate = new RestTemplate();
        // http请求的地址+参数
        String url = "http://localhost:8091/develop/developAuth/"+username+"/"+password;
        return restTemplate.getForObject(url, Developer.class);
    }
}
