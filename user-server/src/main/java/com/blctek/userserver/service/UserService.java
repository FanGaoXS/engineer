package com.blctek.userserver.service;

import com.blctek.userserver.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/02/16:51
 * @Description:
 */
@Service
public interface UserService {

    /**
     * 根据用户名和密码来验证用户身份
     * @param username 用户名
     * @param password 密码
     * @return
     */
    User verify(String username, String password);
}
