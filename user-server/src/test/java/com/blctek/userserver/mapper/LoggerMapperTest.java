package com.blctek.userserver.mapper;

import com.blctek.userserver.pojo.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/04/16:07
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class LoggerMapperTest {

    @Autowired
    private LoggerMapper loggerMapper;

    @Test
    public void insertOne() {
        Logger logger = new Logger();
        logger.setName("退出");
        logger.setExecutor("吴青珂");
        logger.setTime(new Date());
        loggerMapper.insertOne(logger);
    }

    @Test
    public void deleteOne() {
        Logger logger = new Logger();
        logger.setId(3);
        loggerMapper.deleteOne(logger);
    }

    @Test
    public void updateOne() {
        Logger logger = new Logger();
        logger.setId(3);
        logger.setName("登录");
        logger.setExecutor("wqk");
        loggerMapper.updateOne(logger);
    }

    @Test
    public void selectList() {
        List<Logger> loggerList = loggerMapper.selectList();
        loggerList.forEach(logger -> {
            System.out.println(logger);
        });
    }

    @Test
    public void selectListByCondition() {
        Logger logger = new Logger();
        logger.setName("登录");
        List<Logger> loggerList = loggerMapper.selectListByCondition(logger);
        loggerList.forEach(logger1 -> {
            System.out.println(logger1);
        });
    }

    @Test
    public void selectOneByCondition() {
        Logger logger = new Logger();
        logger.setName("退出");
        Logger logger1 = loggerMapper.selectOneByCondition(logger);
        System.out.println(logger1);
    }
}