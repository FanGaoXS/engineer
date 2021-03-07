package com.blctek.userserver.mapper;

import com.blctek.userserver.pojo.Logger;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/04/15:45
 * @Description: 对logger表的增删改查
 */
@Mapper
@Repository
public interface LoggerMapper {

    /**
     * 新增一条日志
     * @param logger   日志对象
     * @return  成功增加的日志数
     */
    Integer insertOne(Logger logger);

    /**
     * 删除一条日志
     * @param logger   日志对象
     * @return  成功删除的日志数
     */
    Integer deleteOne(Logger logger);

    /**
     * 修改一条日志
     * @param logger   日志对象
     * @return  成功修改的日志数
     */
    Integer updateOne(Logger logger);

    /**
     * 查询日志（可分页，可单条件查询，可多条件）
     * @return  日志集合
     */
    List<Logger> selectList(Logger logger);

    /**
     * 查询一条日志记录（可多条件，可单条件）
     * @param logger    日志对象
     * @return  一条日志对象
     */
    Logger selectOne(Logger logger);

    /**
     * 查询记录数（可单条件查询，可多条件）
     * @param logger    日志对象
     * @return 记录数
     */
    Long count(Logger logger);
}
