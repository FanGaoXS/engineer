package com.blctek.authserver.controller;

import com.blctek.authserver.pojo.Developer;
import com.blctek.authserver.utils.HttpUtils;
import com.blctek.authserver.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/01/07/16:55
 * @Description:
 */
@RestController
@Slf4j
public class LoginController {

    @PostMapping("/auth")
    public Map<String,Object> auth(@RequestParam("username")String username,
                                    @RequestParam("password")String password){
        Developer developer = HttpUtils.developerAuth(username,password);
        HashMap<String, Object> resMap = new HashMap<>();
        if (developer==null) {  //开发人员为空（说明没有查询到）
            resMap.put("status",false);
            resMap.put("msg","认证失败！");
            return resMap;
        }
        //  如果开发人员查询到了，则将payload塞进token中
        HashMap<String, String> payloadMap = new HashMap<>();
        payloadMap.put("username",developer.getUid());
        payloadMap.put("cn",developer.getCn());
        String token = JWTUtils.createToken(payloadMap);
        resMap.put("status",true);
        resMap.put("msg",developer.getCn()+"认证成功！");
        resMap.put("token",token);
        return resMap;
    }
}
