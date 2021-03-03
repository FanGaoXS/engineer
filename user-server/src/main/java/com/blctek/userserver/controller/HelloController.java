package com.blctek.userserver.controller;

import com.blctek.commonserver.response.ResultResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/01/15:36
 * @Description:
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/")
public class HelloController {

    @RequestMapping("/")
    public ResultResponse hello(){
        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setMsg("这是user-server服务器");
        return resultResponse;
    }
}
