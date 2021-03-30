package com.blctek.userserver.service;

import com.blctek.userserver.pojo.Salt;
import com.blctek.userserver.vo.VoSalt;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/30/12:09
 * @Description:
 */
@Service
public interface SaltService {

    /**
     * 根据用户名查询他的密码盐
     * @param username                 用户名
     * @return
     */
    VoSalt selectSaltByUsername(String username);
}
