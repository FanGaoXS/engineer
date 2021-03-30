package com.blctek.userserver.service.impl;

import com.blctek.userserver.mapper.SaltMapper;
import com.blctek.userserver.mapper.UserMapper;
import com.blctek.userserver.pojo.Salt;
import com.blctek.userserver.pojo.User;
import com.blctek.userserver.service.UserService;
import com.blctek.userserver.utils.Md5Utils;
import com.blctek.userserver.vo.VoUser;
import com.blctek.userserver.vo.VoUserUpdate;
import com.blctek.userserver.vo.VoUserUpdatePassword;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SaltMapper saltMapper;

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
    public User selectUserById(Integer id) {
        User user = new User();
        user.setId(id);
        return userMapper.selectOne(user);
    }

    @Override
    public User selectUserByIdAndPassword(VoUserUpdatePassword voUserUpdatePassword) {
        User user = new User();
        user.setId(voUserUpdatePassword.getId());
        user.setPassword(voUserUpdatePassword.getOldPassword());
        return userMapper.selectOne(user);
    }

    @Override
    public Boolean insertUser(VoUser voUser) {
        try {
            User user = new User();
            user.setUuid(UUID.randomUUID().toString());
            user.setUsername(voUser.getUsername());
            Integer randomSalt = Md5Utils.randomSalt();
            String password = voUser.getUsername()+"123";                   //初始密码为：用户名+123
            String md5Password = Md5Utils.md5Password(password,randomSalt); //密码+随机盐生成md5
            user.setPassword(md5Password);
            user.setName(voUser.getName());
            user.setPhone(voUser.getPhone());
            user.setRoleId(voUser.getRole().getId());
            userMapper.insertOne(user);

            Salt salt = new Salt();
            salt.setId(user.getId()); //将成功插入的用户的id作为盐的id存入
            salt.setUsername(voUser.getUsername());
            salt.setValue(randomSalt);
            saltMapper.insertOne(salt);

            return true;
        } catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); //事务回滚
        }
        return false;
    }

    @Override
    public Boolean deleteUser(Integer id) {
        try {
            User user = new User();
            user.setId(id);
            userMapper.deleteOne(user);

            Salt salt = new Salt();
            salt.setId(user.getId());
            saltMapper.deleteOne(salt);

            return true;
        } catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return false;
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
    public Boolean updateUserPassword(VoUserUpdatePassword voUserUpdatePassword) {
        User user = new User();
        user.setId(voUserUpdatePassword.getId());
        user.setPassword(voUserUpdatePassword.getNewPassword());
        return userMapper.updateOne(user) > 0;
    }

    @Override
    public Boolean updateUserInfo(VoUserUpdate voUserUpdate) {
        User user = new User();
        user.setId(voUserUpdate.getId());
        user.setName(voUserUpdate.getName());
        user.setPhone(voUserUpdate.getPhone());
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
