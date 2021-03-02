package com.blctek.userserver.mapper;

import com.blctek.userserver.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/02/14:37
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void insertOne() {
        User user = new User();
        user.setUuid(UUID.randomUUID().toString());
        user.setUsername("username");
        user.setPassword("password"+Math.random());
        userMapper.insertOne(user);
    }

    @Test
    public void deleteOne() {
        User user = new User();
        user.setId(1);
        int i = userMapper.deleteOne(user);
        System.out.println(i);
    }

    @Test
    public void updateOne() {
        User user = new User();
        user.setId(2);
        user.setUsername("test");
        user.setPassword("test");
        int i = userMapper.updateOne(user);
        System.out.println(i);
        System.out.println(user);
    }

    @Test
    public void selectList() {
        List<User> userList = userMapper.selectList();
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    public void selectOneByCondition() {
        User user = new User();
        user.setUsername("username0.17605082082300338");
        User user1 = userMapper.selectOneByCondition(user);
        System.out.println(user1);
    }

    @Test
    public void selectListByCondition() {
        User user = new User();
        user.setUsername("username0.17605082082300338");
        List<User> userList = userMapper.selectListByCondition(user);
        for (User user1 : userList) {
            System.out.println(user1);
        }
    }
}