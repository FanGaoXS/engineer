package com.blctek.userserver.service;

import com.blctek.userserver.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/02/16:59
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;
    @Test
    public void verify() {
        User user = userService.verify("test", "test1");
        if (user==null){
            System.out.println("用户不存在");
        }
        System.out.println("用户存在");
        System.out.println(user);
    }
}