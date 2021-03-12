package com.blctek.userserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.blctek.commonserver",
        "com.blctek.userserver"
})
@MapperScan({
        "com.blctek.commonserver.mapper",
        "com.blctek.userserver.mapper"
})
public class UserServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServerApplication.class, args);
    }

}
