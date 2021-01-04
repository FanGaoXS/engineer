package com.blctek.carserver.utils;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/01/04/12:33
 * @Description:
 */
public class HttpUtils {
    //利用http发送put请求
    public static ResponseEntity<String> updateDevByPut(String chipId,String plateNumber){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://172.16.0.95:8880/v1/vn/" + chipId; // http请求的地址
        HttpHeaders headers = new HttpHeaders();    //http请求的头（contentType等）
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);// "application/json;charset=utf-8;"
        HttpEntity<String> entity = new HttpEntity<>(plateNumber, headers); //将headers和body封装到一起
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class); //http请求的返回结果
        return responseEntity;
    }
}
