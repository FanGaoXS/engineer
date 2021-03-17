package com.bltek.speedserver.controller;

import com.blctek.commonserver.response.ResultResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/17/11:49
 * @Description:
 */
@RequestMapping("/")
@CrossOrigin("*")
@RestController
public class IndexController {
    @GetMapping("/")
    public ResultResponse index(){
        return new ResultResponse()
                .setMessage("这是speed-server服务器")
                .setData(true);
    }
}
