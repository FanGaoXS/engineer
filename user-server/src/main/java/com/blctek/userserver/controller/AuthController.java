package com.blctek.userserver.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.blctek.commonserver.response.ResultResponse;
import com.blctek.commonserver.utils.JWTUtils;
import com.blctek.userserver.pojo.User;
import com.blctek.userserver.service.UserService;
import com.blctek.userserver.vo.VoToken;
import com.blctek.userserver.vo.VoLogin;
import com.blctek.userserver.vo.VoUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/02/12:07
 * @Description: vue-admin-template前端框架的认证模块
 */
@RestController
@RequestMapping("/user")
@CrossOrigin("*")
@Slf4j
public class AuthController {

    @Autowired
    private UserService userService;

    /**
     * 登录认证操作：用户输入用户名和密码验证成功就下发基于jwt的token
     * @param voLogin
     * @return
     */
    @PostMapping("/login")
    public ResultResponse login(@RequestHeader("X-Client")String clientName,
                                @RequestBody VoLogin voLogin){
        User dbUser = userService.verify(voLogin.getUsername(), voLogin.getPassword()); //根据用户名和密码去数据中查找
        ResultResponse resultResponse = new ResultResponse();
        if (dbUser!=null){ //用户存在
            log.info("用户[{}]认证成功，此次登录时效[{}]天，从[{}]客户端登录",
                    voLogin.getUsername(),voLogin.getValidityDay(),clientName);
            HashMap<String, String> payloadMap = new HashMap<>();
            payloadMap.put("uuid",dbUser.getUuid());//将用户唯一标识uuid存进JWT的payload中
            payloadMap.put("name",dbUser.getName());//将用户的姓名存进JWT的payload中
            payloadMap.put("username",dbUser.getUsername());//将用户的用户名存进JWT的payload中
            String token = JWTUtils.createToken(payloadMap,voLogin.getValidityDay()); //利用payload和有效期生成token
            resultResponse.setData(new VoToken(token));
            resultResponse.setMessage("用户名和密码认证成功");
        } else { //用户不存在
            log.info("用户名[{}]认证失败，从[{}]客户端登录",
                    voLogin.getUsername(),clientName);
            resultResponse.setCode(20001);
            resultResponse.setMessage("用户名和密码认证失败，用户不存在");
        }
        return resultResponse;
    }

    /**
     * 从请求的headers里获取key为X-Token的token
     * @param voToken token
     * @return
     */
    @PostMapping("/logout")
    public ResultResponse logout(@RequestHeader("X-Client")String clientName,
                                 @RequestHeader("X-Token")VoToken voToken){
        DecodedJWT decodedJWT = JWTUtils.getTokenInfo(voToken.getToken());
        String username = decodedJWT.getClaim("username").asString(); //从payload中取出name
        log.info("用户[{}]从[{}]客户端退出",
                username,clientName);
        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setMessage("用户成功退出");
        resultResponse.setCode(20000);
        resultResponse.setData(username);
        return resultResponse;
    }

    /**
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
            String username = decodedJWT.getClaim("username").asString(); //从payload中取出username
            User dbUser = userService.selectUserByUuid(uuid); //根据uuid查询用户是否存在
            if (dbUser!=null){ //token有效且用户存在
                log.info("用户[{}]存在，TA提交的token是[{}]",
                        username,token);
                resultResponse.setMessage("token有效且用户存在");
                resultResponse.setCode(20000);
                VoUserInfo voUserInfo = new VoUserInfo();
                voUserInfo.setName(dbUser.getName());
                voUserInfo.setPhone(dbUser.getPhone());
                voUserInfo.setAvatar(dbUser.getAvatar());
                resultResponse.setData(voUserInfo);
            } else { //token有效但用户不存在
                log.info("用户[{}]不存在，TA提交的token是[{}]",
                        username,token);
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


}
