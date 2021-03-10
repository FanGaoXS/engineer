package com.blctek.engineserver.controller;

import com.blctek.commonserver.response.ResultResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/10/14:16
 * @Description:
 */
@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class HelloController {
    @GetMapping("/")
    public ResultResponse hello(){
        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setMessage("这是engine-server服务器");
        return resultResponse;
    }
}
