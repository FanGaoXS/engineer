package com.blctek.userserver.service.impl;

import com.blctek.userserver.mapper.LoggerMapper;
import com.blctek.userserver.pojo.Logger;
import com.blctek.userserver.service.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/05/13:28
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
