package com.blctek.userserver.mapper;

import com.blctek.userserver.pojo.Salt;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/29/17:17
 * @Description:
 */
@Mapper
@Repository
public interface SaltMapper {

    /**
     * 增加一条记录
     *
     * @param salt 随机盐对象
     * @return 增加成功的记录数
     */
    Integer insertOne(Salt salt);

    /**
     * 删除一条记录
     *
     * @param salt 随机盐对象
     * @return 删除成功的记录数
     */
    Integer deleteOne(Salt salt);

    /**
     * 查询一条salt记录
     *
     * @param salt 随机盐对象
     * @return 一条随机盐对象
     */
    Salt selectOne(Salt salt);

}
