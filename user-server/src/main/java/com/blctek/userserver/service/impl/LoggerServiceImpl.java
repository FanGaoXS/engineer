package com.blctek.userserver.service.impl;

import com.blctek.commonserver.mapper.LoggerMapper;
import com.blctek.commonserver.pojo.Logger;
import com.blctek.userserver.service.LoggerService;
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
