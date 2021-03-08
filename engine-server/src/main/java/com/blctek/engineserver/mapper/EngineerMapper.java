package com.blctek.engineserver.mapper;

import com.blctek.engineserver.pojo.Engineer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/08/19:33
 * @Description:    对engineer表的增删改查
 */
@Mapper
@Repository
public interface EngineerMapper {

    /**
     * 增加一条记录
     *
     * @param engineer 工程用具对象
     * @return 增加成功的记录数
     */
    Integer insertOne(Engineer engineer);

    /**
     * 删除一条记录
     *
     * @param engineer 工程用具对象
     * @return 删除成功的记录数
     */
    Integer deleteOne(Engineer engineer);

    /**
     * 修改一条记录
     *
     * @param engineer 工程用具对象
     * @return 修改成功的记录数
     */
    Integer updateOne(Engineer engineer);

    /**
     * 查询所有集合（可分页，可多条件，可单条件）
     *
     * @param engineer 工程用具对象
     * @return engineer集合
     */
    List<Engineer> selectList(Engineer engineer);

    /**
     * 查询一条engineer记录（可多条件，可单条件）
     *
     * @param engineer 工程用具对象
     * @return 一条工程用具对象
     */
    Engineer selectOne(Engineer engineer);

    /**
     * 查询记录数（可条件查询）
     *
     * @param engineer 工程用具对象
     * @return 记录数
     */
    Long count(Engineer engineer);

}
