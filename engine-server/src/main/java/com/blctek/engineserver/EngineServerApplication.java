package com.blctek.engineserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.blctek.commonserver",
        "com.blctek.engineserver"
})
@MapperScan({
        "com.blctek.commonserver.mapper",
        "com.blctek.engineserver.mapper"
})
public class EngineServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EngineServerApplication.class, args);
    }

}
