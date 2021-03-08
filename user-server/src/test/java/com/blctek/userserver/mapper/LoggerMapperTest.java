package com.blctek.userserver.mapper;

import com.blctek.userserver.pojo.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/05/17:52
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class LoggerMapperTest {

    @Autowired
    private LoggerMapper loggerMapper;

    @Test
    public void insertOne() {
    }

    @Test
    public void deleteOne() {
    }

    @Test
    public void updateOne() {
    }

    @Test
    public void selectList() {
        Logger logger = new Logger();
        logger.setExecutor("管理员");
        logger.setCurrentPage(1);
        logger.setPageSize(3);
        System.out.println(logger.getPageSize());
        System.out.println(logger.getCurrentPage());
        List<Logger> loggerList = loggerMapper.selectList(logger);
        for (Logger logger1 : loggerList) {
            System.out.println("logger1 = " + logger1);
        }
    }

    @Test
    public void count() {
        Logger logger = new Logger();
        logger.setExecutor("管理员");
        Long count = loggerMapper.count(logger);
        System.out.println(count);

    }
}