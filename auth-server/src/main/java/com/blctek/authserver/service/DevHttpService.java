package com.blctek.authserver.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/01/10/21:41
 * @Description:
 */
@Service
public class DevHttpService {

    //device服务器
    public static String DEV_BASE_URL = "http://172.16.0.95:8880";

    /**
     * 向设备服务器发起请求，提交设备编号和车牌号到设备服务器中
     * @param chipId
     * @param plateNumber
     * @return  http响应体
     */
    public ResponseEntity<String> devInsert(String chipId, String plateNumber){
        RestTemplate restTemplate = new RestTemplate();
        String url = DEV_BASE_URL + "/v1/vn/" + chipId; // http请求的地址
        HttpHeaders headers = new HttpHeaders();    //http请求的头（contentType等）
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);// "application/json;charset=utf-8;"
        HttpEntity<String> entity = new HttpEntity<>(plateNumber, headers); //将headers和body封装到一起
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class); //http请求的返回结果
        return responseEntity;
    }
}
