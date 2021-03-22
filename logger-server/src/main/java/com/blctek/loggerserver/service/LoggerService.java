package com.blctek.loggerserver.service;

import com.blctek.commonserver.pojo.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/22/14:12
 * @Description:
 */
@Service
public interface LoggerService {
    /**
     * 查询日志列表（可分页）
     * @param currentPage               当前页
     * @param pageSize                  每页记录数
     * @return                          日志集合
     */
    List<Logger> selectList(Integer currentPage,
                            Integer pageSize);

    /**
     * 根据条件查询日志列表（可分页）
     * @param executor                  执行人
     * @param client                    来自客户端
     * @param currentPage               当前页
     * @param pageSize                  每页记录数
     * @return                          日志集合
     */
    List<Logger> selectListByCondition(String executor,
                                       String client,
                                       Integer currentPage,
                                       Integer pageSize);

    /**
     * 返回记录数
     * @param logger                    日志对象
     * @return                          记录数
     */
    Long selectTotalSize(Logger logger);
}
