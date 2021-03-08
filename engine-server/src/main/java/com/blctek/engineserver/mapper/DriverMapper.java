package com.blctek.engineserver.mapper;

import com.blctek.engineserver.pojo.Driver;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/08/20:05
 * @Description:    对driver表的增删改查
 */
@Mapper
@Repository
public interface DriverMapper {

    /**
     * 增加一条记录
     *
     * @param driver 驾驶员对象
     * @return 增加成功的记录数
     */
    Integer insertOne(Driver driver);

    /**
     * 删除一条记录
     *
     * @param driver 驾驶员对象
     * @return 删除成功的记录数
     */
    Integer deleteOne(Driver driver);

    /**
     * 修改一条记录
     *
     * @param driver 驾驶员对象
     * @return 修改成功的记录数
     */
    Integer updateOne(Driver driver);

    /**
     * 查询所有集合（可分页，可多条件，可单条件）
     *
     * @param driver 驾驶员对象
     * @return driver集合
     */
    List<Driver> selectList(Driver driver);

    /**
     * 查询一条driver记录（可多条件，可单条件）
     *
     * @param driver 驾驶员对象
     * @return 一条驾驶员对象
     */
    Driver selectOne(Driver driver);

    /**
     * 查询记录数（可条件查询）
     *
     * @param driver 驾驶员对象
     * @return 记录数
     */
    Long count(Driver driver);

}
