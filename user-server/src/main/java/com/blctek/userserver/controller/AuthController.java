package com.blctek.userserver.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.blctek.commonserver.response.ResultResponse;
import com.blctek.commonserver.utils.JWTUtils;
import com.blctek.userserver.anno.AuthLog;
import com.blctek.userserver.anno.CrudLog;
import com.blctek.userserver.pojo.User;
import com.blctek.userserver.service.UserService;
import com.blctek.userserver.vo.VoToken;
import com.blctek.userserver.vo.VoUser;
import com.blctek.userserver.vo.VoUserLogin;
import com.blctek.userserver.vo.VoUserUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/02/12:07
 * @Description: 前端框架的认证模块
 */
@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private UserService userService;//用户表的业务

    /**
     * /auth/login
     * 登录认证操作：用户输入用户名和密码验证成功就下发基于jwt的token
     * @param voUserLogin   登录表单对象
     * @return
     */
    @AuthLog("登录")
    @PostMapping("/login")
    public ResultResponse login(@RequestBody VoUserLogin voUserLogin){
        String message = "";
        Object data = null;
        int code = 20001;
        User dbUser = userService.verify(voUserLogin.getUsername(), voUserLogin.getPassword()); //根据用户名和密码去数据中查找
        if (dbUser!=null){ //用户存在
            HashMap<String, String> payloadMap = new HashMap<>();
            payloadMap.put("uuid",dbUser.getUuid());//将用户唯一标识uuid存进JWT的payload中
            payloadMap.put("name",dbUser.getName());//将用户的姓名存进JWT的payload中
            payloadMap.put("role",dbUser.getRole().getName());//将角色存进JWT的payload中
            payloadMap.put("username",dbUser.getUsername());//将用户的用户名存进JWT的payload中
            String token = JWTUtils.createToken(payloadMap, voUserLogin.getValidityDay()); //利用payload和有效期生成token
            data = new VoToken(token);//用户存在data为含有uuid、name、username、role的token
            message = "用户登录";
            code = 20000;
        } else { //用户不存在，data为null
            message = "用户名和密码认证失败，用户不存在";
        }
        return new ResultResponse()
                .setData(data)
                .setMessage(message)
                .setCode(code);
    }

    /**
     * /auth/logout
     * 从请求的headers里获取key为X-Token的token
     * @param voToken token
     * @return
     */
    @AuthLog("退出")
    @PostMapping("/logout")
    public ResultResponse logout(@RequestHeader("X-Token")VoToken voToken){
        String token = voToken.getToken();
        // 利用用户的token进行相关服务端退出操作
        return new ResultResponse()
                .setData(new VoToken(token))//data返回token
                .setMessage("用户成功退出")
                .setCode(20000);
    }

}
