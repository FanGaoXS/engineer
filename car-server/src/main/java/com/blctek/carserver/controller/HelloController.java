package com.blctek.carserver.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2020/12/30/16:19
 * @Description:
 */
@RestController
@CrossOrigin(origins = "*")
public class HelloController {

    @GetMapping("/")
    public Map<String,Object> hello(){
        HashMap<String, Object> resMap = new HashMap<>();
        resMap.put("status",true);
        resMap.put("msg","这是car-server服务器");
        return resMap;
    }
    /*@RequestMapping("/test")
    public Map<String,Object> test(String plateNumber,String chipId){
        System.out.println(plateNumber);
        System.out.println(chipId);

        RestTemplate restTemplate = new RestTemplate();
        String url="http://172.16.0.95:8880/v1/vn/"+chipId; //http请求的地址
        HttpHeaders headers = new HttpHeaders();    //http请求的headers
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<>(plateNumber, headers); //http请求的体
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);   //请求的结果

        HashMap<String, Object> resMap = new HashMap<>();
        resMap.put("status",true);
        resMap.put("msg","这是一条测试信息");
        resMap.put("resData",responseEntity);
        resMap.put("reqData",entity);
        return resMap;
    }*/
}
