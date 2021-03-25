package com.blctek.userserver.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.blctek.commonserver.response.ResultResponse;
import com.blctek.commonserver.utils.JWTUtils;
import com.blctek.userserver.anno.AuthLog;
import com.blctek.userserver.mapper.UserMapper;
import com.blctek.userserver.pojo.User;
import com.blctek.userserver.service.UserService;
import com.blctek.userserver.vo.VoToken;
import com.blctek.userserver.vo.VoLogin;
import com.blctek.userserver.vo.VoUser;
import com.blctek.userserver.vo.VoUserInfo;
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
     * @param voLogin   登录表单对象
     * @return
     */
    @AuthLog("登录")
    @PostMapping("/login")
    public ResultResponse login(@RequestBody VoLogin voLogin){
        User dbUser = userService.verify(voLogin.getUsername(), voLogin.getPassword()); //根据用户名和密码去数据中查找
        ResultResponse resultResponse = new ResultResponse();
        if (dbUser!=null){ //用户存在
            HashMap<String, String> payloadMap = new HashMap<>();
            payloadMap.put("uuid",dbUser.getUuid());//将用户唯一标识uuid存进JWT的payload中
            payloadMap.put("name",dbUser.getName());//将用户的姓名存进JWT的payload中
            payloadMap.put("role",dbUser.getRole().getName());//将角色存进JWT的payload中
            payloadMap.put("username",dbUser.getUsername());//将用户的用户名存进JWT的payload中
            String token = JWTUtils.createToken(payloadMap,voLogin.getValidityDay()); //利用payload和有效期生成token
            resultResponse.setData(new VoToken(token));
            resultResponse.setMessage("用户名和密码认证成功");
            //用户存在data为含有uuid、name、username、role的token
        } else { //用户不存在
            resultResponse.setCode(20001);
            resultResponse.setMessage("用户名和密码认证失败，用户不存在");
            //用户不存在data为null
        }
        return resultResponse;
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

    /**
     * /auth/info
     * 获取认证用户的基本信息（用于每一次上线进行验证，强调每一次！）：
     * 1、先验证token避免过期，失效，伪造
     * 2、token有效再从token的pyload中取出uuid
     * 3、根据uuid查询数据库中是否存在存在该用户，
     * 存在则返回该用户的信息，否则不返回
     * 为什么先验证了token还要往数据库中查询呢？
     * 因为token里存的是当时下发token那一瞬间的状态
     * 万一在有效期内这个用户被删除或者被修改，即需要a
     * 再次验证该用户的有效性。
     * @param voToken token
     * @return
     */
    @GetMapping("/info")
    public ResultResponse info(@RequestParam("token") VoToken voToken){
        String token = voToken.getToken();
        ResultResponse resultResponse = new ResultResponse();
        try { //验证token
            DecodedJWT decodedJWT = JWTUtils.getTokenInfo(token);
            String uuid = decodedJWT.getClaim("uuid").asString(); //从payload中取出uuid
            //String username = decodedJWT.getClaim("username").asString(); //从payload中取出username
            User dbUser = userService.selectUserByUuid(uuid); //根据uuid查询用户是否存在
            if (dbUser!=null){ //token有效且用户存在
                resultResponse.setMessage("token有效且用户存在");
                resultResponse.setCode(20000);
                VoUserInfo voUserInfo = new VoUserInfo(dbUser);//将查询出来的用户对象赋值给视图用户对象（会将需要的属性赋值）
                resultResponse.setData(voUserInfo);
            } else { //token有效但用户不存在
                resultResponse.setMessage("token有效但用户不存在");
                resultResponse.setCode(20001);
            }
        } catch (RuntimeException e){ //token异常
            e.printStackTrace();
            resultResponse.setMessage(e.getMessage());
            resultResponse.setCode(20001);
        }
        return resultResponse;
    }

    @PostMapping("/update")
    public ResultResponse update(@RequestParam(required = true)Integer id,
                                 @RequestParam(required = true)String uuid,
                                 @RequestParam(required = false)String password,
                                 @RequestParam(required = false)String name,
                                 @RequestParam(required = false)String phone){
        Object data = false;
        String message = "";
        int code = 20000;
        User dbUser = userService.selectUserByIdAndUuid(id, uuid);
        if (dbUser!=null){ //如果数据库中存在在用户
            if (dbUser.getPassword().equals(password)){ //如果将要修改的密码和原数据库密码一致
                data = false;
                message = "新密码与原密码相同";
            } else { //如果新密码与原密码不同或者不修改密码
                try { //捕获修改用户的异常
                    data = userService.updateUserInfo(id, password, name, phone);
                    message = "修改个人信息 ";
                } catch (Exception e){
                    e.printStackTrace();
                    message = e.getMessage().substring(0,150);
                    data = false;
                    code = 20001;
                }
            }
        } else { //数据库中不存在该用户
            code = 20001;
            message = "数据库中不存在该用户 ";
            data = false;
        }
        return new ResultResponse()
                .setCode(code)
                .setMessage(message)
                .setData(data);
    }
}
