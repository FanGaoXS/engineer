package com.blctek.ldapserver.controller;

import com.blctek.ldapserver.pojo.Developer;
import com.blctek.ldapserver.repo.DevelopRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.ldap.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2020/12/22/16:40
 * @Description:    restful对developer的增删改查
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("develop")
@Slf4j
public class DeveloperController {

    @Autowired
    DevelopRepo developRepo;

    /**
     *  restful风格查询所有开发者
     * @return  成功返回开发者列表，失败返回null
     */
    @GetMapping("/developList")
    public List<Developer> developList(){
        try {
            List<Developer> developerList = developRepo.selectAllDeveloper();
            log.info("查询所有开发者成功");
            return developerList;
        } catch (Exception e){
            e.printStackTrace();
            log.info("查询所有开发者失败");
            return null;
        }
    }

    /**
     *  restful更改插入开发者
     * @param commonName    名字
     * @param surName       别名
     * @param username      用户名
     * @param password      密码
     * @return  成功返回true，失败返回false
     */
    @GetMapping("/developInsert/{commonName}/{surName}/{username}/{password}")
    public Boolean developInsert(@PathVariable("commonName")String commonName,
                                 @PathVariable("surName")String surName,
                                 @PathVariable("username")String username,
                                 @PathVariable("password")String password){
        log.info("要插入的开发者cn->[{}]",commonName);
        log.info("要插入的开发者sn->[{}]",surName);
        log.info("要插入的开发者uid->[{}]",username);
        log.info("要插入的开发者password->[{}]",password);
        Developer developer = new Developer();
        developer.setCn(commonName);
        developer.setSn(surName);
        developer.setUid(username);
        developer.setPassword(password);
        try {
            developRepo.insert(developer);
            log.info("插入开发者"+commonName+"成功");
            return true;
        } catch (Exception e){
            e.printStackTrace();
            log.info("插入开发者"+commonName+"失败");
            return false;
        }
    }

    /**
     * restful更改删除开发者
     * @param commonName    开发者姓名
     * @return  成功返回true，失败返回false
     */
    @GetMapping("/developDelete/{commonName}")
    public Boolean developDelete(@PathVariable("commonName") String commonName){
        log.info("要删除的开发者名字->[{}]",commonName);
        Developer developer = new Developer();
        developer.setCn(commonName);
        try {
            developRepo.delete(developer);
            log.info("删除"+commonName+"开发者成功");
            return true;
        } catch (Exception e){
            log.info("删除"+commonName+"开发者失败");
            return false;
        }
    }

    /**
     * restful风格对开发人员身份的验证（如果用户名密码匹配返回developer对象）
     * @param username  用户名
     * @param password  密码
     * @return  开发人员对象
     */
    @GetMapping("/developAuth/{username}/{password}")
    public Developer developAuth(@PathVariable("username")String username,
                                 @PathVariable("password")String password){
        log.info("要查询的用户名->[{}]",username);
        log.info("要查询的密码->[{}]",password);
        try {   //如果用户名和密码匹配正确则返回developer对象
            developRepo.authenticateDeveloper(username,password);
            Developer developer = developRepo.selectOneByUid(username);
            log.info("查询"+username+"开发者成功");
            return developer;
        } catch (EmptyResultDataAccessException e) {    //没有该用户（用户名不存在）
            e.printStackTrace();
            log.info("ldap目录中没有该用户");
            return null;
        } catch (AuthenticationException e) {   // 密码错误
            e.printStackTrace();
            log.info("密码与ldap目录中该用户的密码不匹配");
            return null;
        }
    }
}
