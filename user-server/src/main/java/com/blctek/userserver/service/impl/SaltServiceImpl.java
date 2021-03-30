package com.blctek.userserver.service.impl;

import com.blctek.userserver.mapper.SaltMapper;
import com.blctek.userserver.pojo.Salt;
import com.blctek.userserver.service.SaltService;
import com.blctek.userserver.vo.VoSalt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/30/12:10
 * @Description:
 */
@Service
public class SaltServiceImpl implements SaltService {
    @Autowired
    private SaltMapper saltMapper;
    @Override
    public VoSalt selectSaltByUsername(String username) {
        Salt salt = new Salt();
        salt.setUsername(username);
        Salt dbSalt = saltMapper.selectOne(salt);
        return new VoSalt(dbSalt);
    }
}
