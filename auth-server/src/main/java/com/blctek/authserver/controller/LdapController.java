package com.blctek.authserver.controller;

import com.blctek.authserver.service.LdapHttpService;
import com.blctek.ldapserver.pojo.Developer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/01/08/17:26
 * @Description:
 */
@CrossOrigin(origins = "*")
@RestController
@Slf4j
@RequestMapping("ldap")
public class LdapController {

    @Autowired
    LdapHttpService ldapHttpService;

    @GetMapping("/developList")
    public Map<String,Object> developList(){
        log.info("-----查询所有开发者-----");
        List<Developer> developerList = ldapHttpService.developerList();
        HashMap<String, Object> resMap = new HashMap<>();
        if (developerList==null){
            resMap.put("status",false);
            resMap.put("data",null);
            resMap.put("msg","没有开发者");
            log.info("-----查询开发者失败或为空-----");
            return resMap;
        }
        resMap.put("status",true);
        resMap.put("data",developerList);
        resMap.put("msg","查询开发者列表");
        log.info("-----查询开发者成功-----");
        return resMap;
    }

    @GetMapping("/developDelete")
    public Map<String,Object> developDelete(@RequestParam("commonName") String commonName){
        log.info("-----准备删除"+commonName+"开发者-----");
        HashMap<String, Object> resMap = new HashMap<>();
        Boolean deleteResult = ldapHttpService.developDelete(commonName);
        if (deleteResult) {
            resMap.put("status",true);
            resMap.put("msg","删除"+commonName+"开发者成功");
            log.info("-----删除"+commonName+"开发者成功-----");
            return resMap;
        }
        resMap.put("status",false);
        resMap.put("msg","删除"+commonName+"开发者失败");
        log.info("-----删除"+commonName+"开发者失败-----");
        return resMap;
    }

    @GetMapping("/developInsert")
    public Map<String,Object> developInsert(@RequestParam("commonName")String commonName,
                                            @RequestParam("surName")String surName,
                                            @RequestParam("username")String username,
                                            @RequestParam("password")String password){
        log.info("-----准备新增开发者-----");
        log.info("名字->[{}]",commonName);
        log.info("别名->[{}]",surName);
        log.info("用户名->[{}]",username);
        log.info("密码->[{}]",password);
        Boolean insertResult = ldapHttpService.developInsert(commonName, surName, username, password);
        HashMap<String, Object> resMap = new HashMap<>();
        if (insertResult) {
            resMap.put("status",true);
            resMap.put("msg","新增"+commonName+"开发者成功");
            log.info("-----新增"+commonName+"开发者成功-----");
            return resMap;
        }
        resMap.put("status",false);
        resMap.put("msg","新增"+commonName+"开发者失败");
        log.info("-----新增"+commonName+"开发者失败-----");
        return resMap;
    }

}
