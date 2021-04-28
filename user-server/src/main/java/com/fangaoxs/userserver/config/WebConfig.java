package com.fangaoxs.userserver.config;

import com.fangaoxs.userserver.interceptor.CrudInterceptor;
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
        pathList.add("/role/**");                       //要拦截/role/下的所有请求
        pathList.add("/user/**");                         //要拦截/user/下的所有请求
        List<String> excludePathList = new ArrayList<>();   //不过滤的请求集合
        excludePathList.add("/role/select*");           //不过滤/role/下的查询的请求
        excludePathList.add("/user/select*");             //不过滤/user/下的所有查询的请求
        registry.addInterceptor(crudInterceptor)
                .addPathPatterns(pathList)
                .excludePathPatterns(excludePathList);
    }
}
