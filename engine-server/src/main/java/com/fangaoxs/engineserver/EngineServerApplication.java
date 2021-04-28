package com.fangaoxs.engineserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.fangaoxs.commonserver",//需要用到其中的logger
        "com.fangaoxs.engineserver"
})
@MapperScan({
        "com.fangaoxs.commonserver.mapper",//需要用到其中的loggerMapper
        "com.fangaoxs.engineserver.mapper"
})
public class EngineServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EngineServerApplication.class, args);
    }

}
