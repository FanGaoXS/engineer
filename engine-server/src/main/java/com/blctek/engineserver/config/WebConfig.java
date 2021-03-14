package com.blctek.engineserver.config;

import com.blctek.engineserver.interceptor.CrudInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/12/13:08
 * @Description:
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private CrudInterceptor crudInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> pathList = new ArrayList<>();          //要拦截的请求集合
        pathList.add("/engineer/**");                       //要拦截/engineer/下的所有请求
        pathList.add("/driver/**");                         //要拦截/driver/下的所有请求
        pathList.add("/model/**");                          //要拦截/model/下的所有请求
        List<String> excludePathList = new ArrayList<>();   //不过滤的请求集合
        excludePathList.add("/engineer/select*");           //不过滤/engineer/下的查询的请求
        excludePathList.add("/driver/select*");             //不过滤/driver/下的所有查询的请求
        excludePathList.add("/model/select*");              //不过滤/model/下的所有查询的请求
        registry.addInterceptor(crudInterceptor)
                .addPathPatterns(pathList)
                .excludePathPatterns(excludePathList);
    }
}
