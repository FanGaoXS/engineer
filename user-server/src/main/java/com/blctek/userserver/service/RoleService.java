package com.blctek.userserver.service;

import com.blctek.userserver.pojo.Role;
import com.blctek.userserver.vo.VoRole;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/23/12:28
 * @Description:    角色表的业务
 */
@Service
public interface RoleService {

    /**
     * 插入角色
     * @param voRole                  角色对象
     * @return                      true成功，false失败
     */
    Boolean insertRole(VoRole voRole);

    /**
     * 删除一个角色
     * @param id                    角色编号
     * @return                      true成功，false失败
     */
    Boolean deleteRole(Integer id);

    /**
     * 修改一个角色
     * @param voRole                  角色对象
     * @return                      true成功，false失败
     */
    Boolean updateRole(VoRole voRole);

    /**
     * 查询所有角色（可分页）
     * @param currentPage           当前页
     * @param pageSize              每页记录数
     * @return                      角色集合
     */
    List<VoRole> selectList(Integer currentPage,
                          Integer pageSize);

    /**
     * 查询角色记录数
     * @return                      记录数
     */
    Long selectTotalSize();
}
