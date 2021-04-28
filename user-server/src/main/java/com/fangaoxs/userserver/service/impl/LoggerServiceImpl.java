package com.fangaoxs.userserver.service.impl;

import com.fangaoxs.commonserver.mapper.LoggerMapper;
import com.fangaoxs.commonserver.pojo.Logger;
import com.fangaoxs.userserver.service.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/22/14:09
 * @Description:
 */
@Service
public class LoggerServiceImpl implements LoggerService {
    @Autowired
    private LoggerMapper loggerMapper;

    @Override
    public Integer insertLogger(Logger logger) {
        return loggerMapper.insertOne(logger);
    }
}
