package com.blctek.userserver.controller;

import com.blctek.userserver.pojo.User;
import com.blctek.userserver.response.ResultResponse;
import com.blctek.userserver.service.UserService;
import com.blctek.userserver.vo.VoUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/02/12:07
 * @Description: vue-admin-template前端框架的认证模块
 */
@RestController
@RequestMapping("/vue-admin-template/user")
@CrossOrigin("*")
@Slf4j
public class VueAdminTemplateAuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResultResponse login(@RequestBody VoUser voUser){
        log.info("用户的用户名->[{}]",voUser.getUsername());
        log.info("用户的密码->[{}]",voUser.getPassword());
        User user = userService.verify(voUser.getUsername(), voUser.getPassword());
        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setMsg("用户登录");
        if (user!=null){ //用户存在
            HashMap<Object, Object> dataMap = new HashMap<>();
            String token = "admin-token";
            dataMap.put("token",token);
            resultResponse.setData(dataMap);
        } else { //用户不存在
            resultResponse.setCode(20001);
        }
        return resultResponse;
    }

    @RequestMapping("/info")
    public ResultResponse info(){
        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setMsg("用户信息");
        return resultResponse;
    }

    @RequestMapping("/logout")
    public ResultResponse logout(){
        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setMsg("用户退出");
        return resultResponse;
    }


}
