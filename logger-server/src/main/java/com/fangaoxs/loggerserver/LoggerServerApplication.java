package com.fangaoxs.loggerserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.fangaoxs.commonserver",//需要用到其中的logger的pojo
        "com.fangaoxs.loggerserver"
})
@MapperScan({
        "com.fangaoxs.commonserver.mapper",//需要用到其中的loggerMapper
})
public class LoggerServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoggerServerApplication.class, args);
    }

}
