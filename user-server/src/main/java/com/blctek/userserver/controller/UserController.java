package com.blctek.userserver.controller;

import com.blctek.commonserver.response.ResultResponse;
import com.blctek.userserver.pojo.User;
import com.blctek.userserver.service.UserService;
import com.blctek.userserver.vo.list.VoUserInfoList;
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
    @GetMapping("/userList")
    public ResultResponse userList(@RequestParam(required = false)Integer currentPage,
                                   @RequestParam(required = false)Integer pageSize){
        List<User> userList = userService.selectUserList(currentPage, pageSize);
        VoUserInfoList voUserInfoList = new VoUserInfoList(userList);
        Long totalSize = userService.selectTotalSize(new User());
        voUserInfoList.setCurrentPage(currentPage);
        voUserInfoList.setPageSize(pageSize);
        voUserInfoList.setTotalSize(totalSize);
        return new ResultResponse()
                .setMessage("查询用户列表")
                .setData(voUserInfoList);
    }
}
