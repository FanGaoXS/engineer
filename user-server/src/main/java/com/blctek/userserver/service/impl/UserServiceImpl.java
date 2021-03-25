package com.blctek.userserver.service.impl;

import com.blctek.userserver.mapper.UserMapper;
import com.blctek.userserver.pojo.User;
import com.blctek.userserver.service.UserService;
import com.blctek.userserver.vo.VoUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/02/16:56
 * @Description:
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User verify(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return userMapper.selectOne(user);
    }

    @Override
    public User selectUserByUuid(String uuid) {
        User user = new User();
        user.setUuid(uuid);
        return userMapper.selectOne(user);
    }

    @Override
    public User selectUserByIdAndUuid(Integer id, String uuid) {
        User user = new User();
        user.setId(id);
        user.setUuid(uuid);
        return userMapper.selectOne(user);
    }

    @Override
    public Boolean insertUser(VoUser voUser) {
        User user = new User();
        user.setUuid(UUID.randomUUID().toString());
        user.setUsername(voUser.getUsername());
        user.setPassword(voUser.getUsername()+"123"); //初始密码为用户名+123
        user.setName(voUser.getName());
        user.setPhone(voUser.getPhone());
        user.setRoleId(voUser.getRole().getId());
        return userMapper.insertOne(user) > 0;
    }

    @Override
    public Boolean deleteUser(Integer id) {
        User user = new User();
        user.setId(id);
        return userMapper.deleteOne(user) > 0;
    }

    @Override
    public Boolean updateUser(VoUser voUser) {
        User user = new User();
        user.setId(voUser.getId());               //根据id修改
        user.setName(voUser.getName());           //修改名字
        user.setPhone(voUser.getPhone());         //修改用户名
        user.setRoleId(voUser.getRole().getId()); //修改角色
        return userMapper.updateOne(user) > 0;
    }

    @Override
    public Boolean updateUserInfo(Integer id, String password, String name, String phone) {
        User user = new User();
        user.setId(id);
        user.setPassword(password);
        user.setName(name);
        user.setPhone(phone);
        return userMapper.updateOne(user) > 0;
    }

    @Override
    public List<VoUser> selectUserListCondition(Integer roleId,
                                                Integer currentPage,
                                                Integer pageSize) {
        User user = new User();
        user.setRoleId(roleId);
        user.setCurrentPage(currentPage);
        user.setPageSize(pageSize);
        List<User> userList = userMapper.selectList(user);
        ArrayList<VoUser> voUserList = new ArrayList<>();
        userList.forEach(user1 -> voUserList.add(new VoUser(user1)));
        return voUserList;
    }

    @Override
    public Long selectTotalSize(User user) {
        return userMapper.count(user);
    }

}
