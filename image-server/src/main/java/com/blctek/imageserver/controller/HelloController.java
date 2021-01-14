package com.blctek.imageserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/01/13/12:42
 * @Description:
 */
@RestController
public class HelloController {

    @GetMapping("/")
    public Map<String,Object> hello(){
        HashMap<String, Object> resMap = new HashMap<>();
        resMap.put("status",true);
        resMap.put("msg","这是image-server服务器");
        return resMap;
    }

}
