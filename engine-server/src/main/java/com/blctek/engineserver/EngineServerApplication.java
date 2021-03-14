package com.blctek.engineserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.blctek.commonserver",//需要用到其中的logger
        "com.blctek.engineserver"
})
@MapperScan({
        "com.blctek.commonserver.mapper",//需要用到其中的loggerMapper
        "com.blctek.engineserver.mapper"
})
public class EngineServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EngineServerApplication.class, args);
    }

}
