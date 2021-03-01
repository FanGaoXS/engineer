package com.blctek.userserver.controller;

import com.blctek.userserver.response.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/01/17:40
 * @Description:
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/user")
@Slf4j
public class AuthController {

    @RequestMapping("/login")
    public ResultResponse login(@RequestParam("username")String username,
                                @RequestParam("password")String password){
        log.info("用户的用户名->[{}]",username);
        log.info("用户的密码->[{}]",password);
        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setMsg("用户登录");
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
