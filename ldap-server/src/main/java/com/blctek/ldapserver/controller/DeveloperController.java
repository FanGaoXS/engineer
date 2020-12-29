package com.blctek.ldapserver.controller;

import com.blctek.ldapserver.pojo.Developer;
import com.blctek.ldapserver.repo.DevelopRepo;
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
 * @Date: 2020/12/22/16:40
 * @Description:
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("develop")
@Slf4j
public class DeveloperController {
    @Autowired
    DevelopRepo developRepo;
    @RequestMapping("/developList")
    public Map<String,Object> developList(){
        List<Developer> developerList = developRepo.selectAllDeveloper();
        HashMap<String, Object> resMap = new HashMap<>();
        resMap.put("status",true);
        resMap.put("data",developerList);
        resMap.put("msg","查询所有开发人员");
        return resMap;
    }
    @PostMapping("/developInsert")
    public Map<String,Object> developInsert(@RequestParam("commonName")String commonName,
                                @RequestParam("surName")String surName,
                                @RequestParam("userName")String userName,
                                @RequestParam("passWord")String passWord){
        Developer developer = new Developer();
        developer.setCn(commonName);
        developer.setSn(surName);
        developer.setUid(userName);
        developer.setPassword(passWord);
        developRepo.insert(developer);
        HashMap<String, Object> resMap = new HashMap<>();
        resMap.put("status",true);
        resMap.put("msg","新增开发人员成功");
        return resMap;
    }
    @RequestMapping("/developDelete")
    public Map<String,Object> developDelete(@RequestParam("commonName")String commonName){
        Developer developer = new Developer();
        developer.setCn(commonName);
        developRepo.delete(developer);
        HashMap<String, Object> resMap = new HashMap<>();
        resMap.put("status",true);
        resMap.put("msg","删除开发人员成功");
        return resMap;
    }
}
