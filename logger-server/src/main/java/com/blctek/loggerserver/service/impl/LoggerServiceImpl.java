package com.blctek.loggerserver.service.impl;

import com.blctek.commonserver.mapper.LoggerMapper;
import com.blctek.commonserver.pojo.Logger;
import com.blctek.loggerserver.service.LoggerService;
import com.blctek.loggerserver.vo.VoLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/22/14:13
 * @Description:
 */
@Service
public class LoggerServiceImpl implements LoggerService {
    @Autowired
    private LoggerMapper loggerMapper;
    @Override
    public List<Logger> selectList(Integer currentPage, Integer pageSize) {
        Logger logger = new Logger();
        logger.setCurrentPage(currentPage);
        logger.setPageSize(pageSize);
        return loggerMapper.selectList(logger);
    }

    @Override
    public List<VoLogger> selectListByCondition(String executor,
                                              String client,
                                              Integer currentPage,
                                              Integer pageSize) {
        Logger logger = new Logger();
        logger.setExecutor(executor);
        logger.setClient(client);
        logger.setCurrentPage(currentPage);
        logger.setPageSize(pageSize);
        List<Logger> loggerList = loggerMapper.selectList(logger);
        ArrayList<VoLogger> voLoggerList = new ArrayList<>();
        loggerList.forEach(logger1 -> voLoggerList.add(new VoLogger(logger1)));
        return voLoggerList;
    }

    @Override
    public Long selectTotalSize(Logger logger) {
        return loggerMapper.count(logger);
    }
}
