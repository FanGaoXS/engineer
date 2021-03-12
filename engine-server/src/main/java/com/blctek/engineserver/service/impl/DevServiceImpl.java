package com.blctek.engineserver.service.impl;

import com.blctek.engineserver.service.DevService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/11/18:09
 * @Description: 向局域网的服务器发起请求
 */
@Service
public class DevServiceImpl implements DevService {

    //device服务器
    public static String DEV_BASE_URL = "http://172.16.0.95:8880";

    @Override
    public HttpStatus insertDev(String deviceId,String number) {
        RestTemplate restTemplate = new RestTemplate();
        String url = DEV_BASE_URL + "/v1/vn/" + deviceId; // http请求的url
        HttpHeaders headers = new HttpHeaders();    //http请求的头
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);//设置http请求头为json
        //将headers和body封装成完整的http请求
        HttpEntity<String> entity = new HttpEntity<>(number, headers);
        //发起http的put请求，并且返回请求体
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);
        return responseEntity.getStatusCode();//从请求体从获得状态码
    }
}
