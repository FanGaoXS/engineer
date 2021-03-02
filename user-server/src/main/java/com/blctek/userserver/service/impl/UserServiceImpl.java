package com.blctek.userserver.service.impl;

import com.blctek.userserver.mapper.UserMapper;
import com.blctek.userserver.pojo.User;
import com.blctek.userserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/02/16:56
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User verify(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        try {
            User dbUser = userMapper.selectOneByCondition(user);
            return dbUser;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
