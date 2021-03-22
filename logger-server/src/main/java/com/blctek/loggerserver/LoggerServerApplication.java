package com.blctek.loggerserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.blctek.commonserver",//需要用到其中的logger的pojo
        "com.blctek.loggerserver"
})
@MapperScan({
        "com.blctek.commonserver.mapper",//需要用到其中的loggerMapper
})
public class LoggerServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoggerServerApplication.class, args);
    }

}
