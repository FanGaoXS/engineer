package com.blctek.ldapserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        resMap.put("msg","这是ldap-server服务器");
        return resMap;
    }
}