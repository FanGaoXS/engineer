package com.blctek.carserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/01/09/16:35
 * @Description:
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @GetMapping("/get/{username}")
    public String testGet(@PathVariable("username") String username){
        log.info("用户名->[{}]",username);
        return "测试get提交";
    }

    @GetMapping("/post/{username}")
    public String testPost(@PathVariable("username") String username/*,
                           @RequestParam("file") MultipartFile file*/){
        log.info("用户名->[{}]",username);
        /*log.info("文件是否为空->[{}]",file.isEmpty());
        log.info("文件大小->[{}]",file.getSize());*/
        return "测试post提交";
    }
}
