package com.blctek.userserver.controller;

import com.blctek.commonserver.response.ResultResponse;
import com.blctek.commonserver.vo.VoList;
import com.blctek.userserver.service.RoleService;
import com.blctek.userserver.vo.VoRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/23/12:37
 * @Description:
 */
@RestController
@RequestMapping("/role")
@CrossOrigin("*")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @PostMapping("/insertRole")
    public ResultResponse insertRole(@RequestBody VoRole voRole){
//        System.out.println("voRole = " + voRole);
        Boolean result = roleService.insertRole(voRole);
        return new ResultResponse()
                .setData(result)
                .setMessage("新增角色 "+voRole.getChineseName());
    }
    @GetMapping("/deleteRole")
    public ResultResponse deleteRole(@RequestParam(required = true)Integer id){
//        System.out.println("id = " + id);
        Boolean result = roleService.deleteRole(id);
        return new ResultResponse()
                .setData(result)
                .setMessage("删除角色 ");
    }
    @PostMapping("/updateRole")
    public ResultResponse updateRole(@RequestBody VoRole voRole){
//        System.out.println("voRole = " + voRole);
        Boolean result = roleService.updateRole(voRole);
        return new ResultResponse()
                .setData(result)
                .setMessage("修改角色 "+voRole.getChineseName());
    }
    @GetMapping("/selectList")
    public ResultResponse selectList(@RequestParam(required = false)Integer currentPage,
                                     @RequestParam(required = false)Integer pageSize){
//        System.out.println("currentPage = " + currentPage + ", pageSize = " + pageSize);
        List<VoRole> voRoleList = roleService.selectList(currentPage, pageSize);
        Long totalSize = roleService.selectTotalSize();
        VoList<VoRole> voList = new VoList<>();
        voList.setItems(voRoleList);
        voList.setCurrentPage(currentPage);
        voList.setPageSize(pageSize);
        voList.setTotalSize(totalSize);
        return new ResultResponse()
                .setMessage("查询角色列表 ")
                .setData(voList);
    }
}
