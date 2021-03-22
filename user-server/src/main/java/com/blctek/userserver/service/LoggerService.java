package com.blctek.userserver.service;

import com.blctek.commonserver.pojo.Logger;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/22/14:08
 * @Description:
 */
@Service
public interface LoggerService {
    /**
     * 新增一条日志
     * @param logger    日志对象
     * @return  成功新增日志的记录数
     */
    Integer insertLogger(Logger logger);
}
