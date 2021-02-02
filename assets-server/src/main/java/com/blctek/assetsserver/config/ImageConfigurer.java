package com.blctek.assetsserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/01/13/17:03
 * @Description:
 */
@Configuration
public class ImageConfigurer implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //获取文件的真实路径
        //String path = System.getProperty("user.dir")+"\\image-server\\src\\main\\resources\\static\\image\\";
        String winPath = System.getProperty("user.dir")+"\\assets-server";
//        String linuxPath = System.getProperty("user.dir")+"/image-server/image/";
        String linuxPath = System.getProperty("user.dir")+"/assets-server";
        //获取操作系统的名字
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {   //windows系统
            registry.addResourceHandler("/image/**","/zip/**")
                    .addResourceLocations("file:"+winPath);
        }else{//linux和mac系统 可以根据逻辑再做处理
            // handlers表示会拦截的请求，比如会处理/image/**这种样式的请求
            // locations表示会映射到的本地路径
            registry.addResourceHandler("/image/**","/zip/**")
                    .addResourceLocations("file:"+linuxPath+"/image/","file:"+linuxPath+"/zip/");
        }

    }
}
