package com.bltek.speedserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SpeedServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpeedServerApplication.class, args);
    }

}
