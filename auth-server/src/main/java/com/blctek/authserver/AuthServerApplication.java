package com.blctek.authserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 *  不自动dataSource的配置（因为引入了含有dataSourceAutoConfiguration
 *  的依赖项car-server，如果不关闭dataSource自动配置则会在当前项目中也进
 *  行扫描，就会报错）
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class AuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class, args);
    }

}
