package com.blctek.userserver.service.impl;

import com.blctek.userserver.mapper.RoleMapper;
import com.blctek.userserver.pojo.Role;
import com.blctek.userserver.service.RoleService;
import com.blctek.userserver.vo.VoRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/23/12:32
 * @Description:
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public Boolean insertRole(VoRole voRole) {
        Role role = new Role();
        role.setName(voRole.getName());
        role.setChineseName(voRole.getChineseName());
        role.setDescription(voRole.getDescription());
        return roleMapper.insertOne(role) > 0;
    }

    @Override
    public Boolean deleteRole(Integer id) {
        Role role = new Role();
        role.setId(id);
        return roleMapper.deleteOne(role) > 0;
    }

    @Override
    public Boolean updateRole(VoRole voRole) {
        Role role = new Role();
        role.setId(voRole.getId());
        role.setName(voRole.getName());
        role.setChineseName(voRole.getChineseName());
        role.setDescription(voRole.getDescription());
        return roleMapper.updateOne(role) > 0;
    }

    @Override
    public List<VoRole> selectList(Integer currentPage, Integer pageSize) {
        Role role = new Role();
        role.setCurrentPage(currentPage);
        role.setPageSize(pageSize);
        List<Role> roleList = roleMapper.selectList(role);
        List<VoRole> voRoleList = new ArrayList<>();
        roleList.forEach(role1 -> voRoleList.add(new VoRole(role1)));
        return voRoleList;
    }

    @Override
    public Long selectTotalSize() {
        return roleMapper.count(new Role());
    }
}
