package com.blctek.assetsserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class AssetsServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssetsServerApplication.class, args);
    }

}
