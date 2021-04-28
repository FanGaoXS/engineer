package com.fangaoxs.userserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.fangaoxs.commonserver",
        "com.fangaoxs.userserver"
})
@MapperScan({
        "com.fangaoxs.commonserver.mapper",
        "com.fangaoxs.userserver.mapper"
})
public class UserServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServerApplication.class, args);
    }

}
