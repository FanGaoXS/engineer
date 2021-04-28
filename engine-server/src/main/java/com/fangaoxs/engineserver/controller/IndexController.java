package com.fangaoxs.engineserver.controller;

import com.fangaoxs.commonserver.response.ResultResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/15/18:18
 * @Description:
 */
@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class IndexController {
    @RequestMapping("/")
    public ResultResponse index(){
        return new ResultResponse()
                .setData(true)
                .setMessage("这是engine-server服务器");
    }
}
