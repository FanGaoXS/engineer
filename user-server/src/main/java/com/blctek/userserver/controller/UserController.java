package com.blctek.userserver.controller;

import com.blctek.commonserver.response.ResultResponse;
import com.blctek.commonserver.vo.VoList;
import com.blctek.userserver.pojo.User;
import com.blctek.userserver.service.UserService;
import com.blctek.userserver.vo.VoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/22/18:33
 * @Description:
 */
@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/insertUser")
    public ResultResponse insertUser(@RequestBody VoUser voUser){
        System.out.println("voUser = " + voUser);
        Object data = false;
        String message;
        int code = 20000;
        try {
            data = userService.insertUser(voUser);
            message = "新增用户 "+voUser.getName();
        } catch (Exception e){ //异常
            e.printStackTrace();
            code = 20001;
            message = e.getMessage().substring(0,200);
            data = false;
        }
        return new ResultResponse()
                .setCode(code)
                .setMessage(message)
                .setData(data);
    }

    @GetMapping("/deleteUser")
    public ResultResponse deleteUser(@RequestParam(required = true)Integer id){
        System.out.println("id = " + id);
        Object data = false;
        String message;
        int code = 20000;
        try {
            data = userService.deleteUser(id);
            message = "删除用户 ";
        } catch (Exception e){ //异常
            e.printStackTrace();
            data = false;
            message = e.getMessage().substring(0,200);
            code = 20001;
        }
        return new ResultResponse()
                .setCode(code)
                .setMessage(message)
                .setData(data);
    }

    @PostMapping("/updateUser")
    public ResultResponse updateUser(@RequestBody VoUser voUser){
        System.out.println("voUser = " + voUser);
        Object data = false;
        String message;
        int code = 20000;
        try {
            data = userService.updateUser(voUser);
            message = "修改用户 "+voUser.getName();
        } catch (Exception e){ //异常
            e.printStackTrace();
            data = false;
            message = e.getMessage().substring(0,200);
            code = 20001;
        }
        return new ResultResponse()
                .setCode(code)
                .setMessage(message)
                .setData(data);
    }

    @GetMapping("/selectList")
    public ResultResponse selectList(@RequestParam(required = false)Integer roleId,
                                     @RequestParam(required = false)Integer currentPage,
                                     @RequestParam(required = false)Integer pageSize){
        List<VoUser> voUserList = userService.selectUserListCondition(roleId, currentPage, pageSize);
        User user = new User();
        user.setRoleId(roleId);
        Long totalSize = userService.selectTotalSize(user);
        VoList<VoUser> voList = new VoList<>();
        voList.setCurrentPage(currentPage);
        voList.setPageSize(pageSize);
        voList.setTotalSize(totalSize);
        voList.setItems(voUserList);
        return new ResultResponse()
                .setMessage("查询用户列表")
                .setData(voList);
    }
}
