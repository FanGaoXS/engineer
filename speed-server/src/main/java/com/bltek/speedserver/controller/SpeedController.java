package com.bltek.speedserver.controller;

import com.blctek.commonserver.response.ResultResponse;
import com.bltek.speedserver.vo.Content;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/17/11:45
 * @Description:
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/speed")
@Slf4j
public class SpeedController {
    @PostMapping("/uploadTest")
    public ResultResponse uploadSpeedTest(HttpServletRequest request){
        /*log.info("http从客户端发起的时间戳->[{}]",clientTime);
        long serverTime = new Date().getTime() ;
        log.info("http请求到客户端的时间戳->[{}]",serverTime);
        double diffTime = (double)serverTime - clientTime; //客户端到服务端的延迟（单位ms）
        log.info("客户端到服务端的延迟->[{}ms]",diffTime);
        System.out.println(diffTime/1000);
        double contentLengthLong = request.getContentLengthLong(); //http请求的长度（单位为Byte）
        double speed = contentLengthLong / (diffTime / 1000); //每秒钟发送的多少字节（单位Byte/s）
        log.info("（标准算法）客户端向服务端发送请求的速率->[{}Byte/s]",speed);
        log.info("（国家带宽算法）客户端向服务端发送请求的速率->[{}Bps]",speed * 8);
        log.info("（国家带宽算法）客户端向服务端发送请求的速率->[{}KBps]",(speed * 8) / 1024);
        log.info("（国家带宽算法）客户端向服务端发送请求的速率->[{}MBps]",(speed * 8) / 1024 / 1024);
        HashMap<String, Object> resMap = new HashMap<>();
        resMap.put("data",speed);*/
        long contentLength = request.getContentLengthLong();
        log.info("contentLength->[{} Byte]",contentLength);
        log.info("contentLength->[{} Kb]",contentLength/1024);
        log.info("contentLength->[{} Mb]",contentLength/1024/1024);
        Content content = new Content();
        content.setContentLength(contentLength);
        return new ResultResponse()
                .setData(content)
                .setMessage("测试客户端到服务端的上传速率");
    }
}
