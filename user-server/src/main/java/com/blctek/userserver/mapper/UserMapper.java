package com.blctek.userserver.mapper;

import com.blctek.userserver.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/02/14:13
 * @Description: 对user表进行增删改查
 */
@Mapper
@Repository
public interface UserMapper {

    /**
     * 增加用户
     * @param user 用户对象
     * @return 增加成功的记录数
     */
    Integer insertOne(User user);

    /**
     * 删除用户
     * @param user 用户对象
     * @return 删除成功的记录数
     */
    Integer deleteOne(User user);

    /**
     * 修改用户
     * @param user 用户对象
     * @return 修改成功的记录数
     */
    Integer updateOne(User user);

    /**
     * 查询所有user集合
     * @return user集合
     */
    List<User> selectList();

    /**
     * 条件查询一个
     * @param user 用户对象
     * @return user
     */
    User selectOneByCondition(User user);

    /**
     * 条件查询很多个
     * @param user 用户对象
     * @return user集合
     */
    List<User> selectListByCondition(User user);
}
