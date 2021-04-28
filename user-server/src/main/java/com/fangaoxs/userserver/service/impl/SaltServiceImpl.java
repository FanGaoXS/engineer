package com.fangaoxs.userserver.service.impl;

import com.fangaoxs.userserver.mapper.SaltMapper;
import com.fangaoxs.userserver.pojo.Salt;
import com.fangaoxs.userserver.service.SaltService;
import com.fangaoxs.userserver.vo.VoSalt;
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
