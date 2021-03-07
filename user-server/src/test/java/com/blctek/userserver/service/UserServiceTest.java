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
    @Autowired
    private User user;
    @Test
    public void verify() {
        String username = "wuqingke";
        String password = "wuqingke";
        User user = userService.verify(username, password);
        System.out.println(user);
    }

}