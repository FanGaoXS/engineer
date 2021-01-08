package com.blctek.authserver.service;

import com.blctek.ldapserver.pojo.Developer;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/01/08/17:14
 * @Description:    基于http请求的对ldap-server服务器的操作
 */
@Service
public class LdapHttpService {

    //ldap服务器常量
    public static String LDAP_BASE_URL= "http://localhost:8091";

    /**
     * 根据用户名和密码查询是否存在该用户
     * @param username  用户名
     * @param password  密码
     * @return  查询到的Developer对象
     */
    public Developer developerAuth(String username, String password){
        String url = LDAP_BASE_URL+"/develop/developAuth/"
                + username + "/"
                + password ;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, Developer.class);
    }

    /**
     *  查询所有开发者
     * @return  开发者集合
     */
    public List<Developer> developerList(){
        String url = LDAP_BASE_URL+"/develop/developList";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, List.class);
    }

    /**
     * 新增开发者
     * @param commonName
     * @param surName
     * @param username
     * @param password
     * @return  true成功，false失败
     */
    public Boolean developInsert(String commonName,String surName,String username,String password){
        String url = LDAP_BASE_URL+"/develop/developInsert/"
                + commonName + "/"
                + surName + "/"
                + username + "/"
                + password ;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url ,Boolean.class);
    }

    /**
     * 删除开发者
     * @param commonName
     * @return  true成功，false失败
     */
    public Boolean developDelete(String commonName){
        String url = LDAP_BASE_URL+"/develop/developDelete/"
                + commonName ;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url ,Boolean.class);
    }
}
