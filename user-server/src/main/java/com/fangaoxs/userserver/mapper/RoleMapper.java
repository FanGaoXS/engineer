package com.fangaoxs.userserver.mapper;

import com.fangaoxs.userserver.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/23/11:30
 * @Description:
 */
@Mapper
@Repository
public interface RoleMapper {

    /**
     * 增加一条记录
     *
     * @param role 角色对象
     * @return 增加成功的记录数
     */
    Integer insertOne(Role role);

    /**
     * 删除一条记录
     *
     * @param role 角色对象
     * @return 删除成功的记录数
     */
    Integer deleteOne(Role role);

    /**
     * 修改一条记录
     *
     * @param role 角色对象
     * @return 修改成功的记录数
     */
    Integer updateOne(Role role);

    /**
     * 查询所有集合（可分页，可多条件，可单条件）
     *
     * @param role 角色对象
     * @return role集合
     */
    List<Role> selectList(Role role);

    /**
     * 查询一条role记录（可多条件，可单条件）
     *
     * @param role 角色对象
     * @return 一条角色对象
     */
    Role selectOne(Role role);

    /**
     * 查询记录数（可条件查询）
     *
     * @param role 角色对象
     * @return 记录数
     */
    Long count(Role role);

}
