package com.fangaoxs.userserver.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.fangaoxs.commonserver.response.ResultResponse;
import com.fangaoxs.commonserver.utils.JWTUtils;
import com.fangaoxs.userserver.anno.CrudLog;
import com.fangaoxs.userserver.pojo.User;
import com.fangaoxs.userserver.service.SaltService;
import com.fangaoxs.userserver.service.UserService;
import com.fangaoxs.userserver.vo.VoUser;
import com.fangaoxs.userserver.vo.VoUserUpdate;
import com.fangaoxs.userserver.vo.VoUserUpdatePassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/26/14:28
 * @Description:    对于用户基本信息的操作
 */
@RestController
@RequestMapping("/profile")
@CrossOrigin("*")
public class ProfileController {
    @Autowired
    private UserService userService;
    @Autowired
    private SaltService saltService;

    /**
     * /profile/info
     * 获取认证用户的基本信息（用于每一次上线进行验证，强调每一次！）：
     * 1、先验证token避免过期，失效，伪造
     * 2、token有效再从token的pyload中取出uuid
     * 3、根据uuid查询数据库中是否存在存在该用户，
     * 存在则返回该用户的信息，否则不返回
     * 为什么先验证了token还要往数据库中查询呢？
     * 因为token里存的是当时下发token那一瞬间的状态
     * 万一在有效期内这个用户被删除或者被修改，即需要a
     * 再次验证该用户的有效性。
     * @param token token
     * @return
     */
    @GetMapping("/info")
    public ResultResponse info(@RequestParam("token") String token){
        StringBuffer message = new StringBuffer();
        Object data = null;
        int code = 20001;
        ResultResponse resultResponse = new ResultResponse();
        try { //验证token
            DecodedJWT decodedJWT = JWTUtils.getTokenInfo(token);
            String uuid = decodedJWT.getClaim("uuid").asString(); //从payload中取出uuid
            //String username = decodedJWT.getClaim("username").asString(); //从payload中取出username
            User dbUser = userService.selectUserByUuid(uuid); //根据uuid查询用户是否存在
            if (dbUser!=null){ //token有效且用户存在
                message.append("token有效且用户存在");
                code = 20000;
                data = new VoUser(dbUser);//将查询出来的用户对象赋值给视图用户对象（会将需要的属性赋值）
            } else { //token有效但用户不存在
                message.append("token有效但用户不存在");
            }
        } catch (RuntimeException e){ //token异常
            e.printStackTrace();
            message.append(e.getMessage().substring(0,150));
        }
        return new ResultResponse()
                .setData(data)
                .setCode(code)
                .setMessage(message.toString());
    }

    /**
     * 登录或修改密码前需要获取用户密码的随机盐
     * @param username
     * @return
     */
    @GetMapping("/salt")
    public ResultResponse getSalt(@RequestParam(required = true)String username){
        Object data = null;
        int code = 20001;
        StringBuffer message = new StringBuffer("获取 "+username+" 用户的随机盐");
        try {
            data = saltService.selectSaltByUsername(username);
            code = 20000;
        } catch (Exception e){
            e.printStackTrace();
            message.append(e.getMessage().substring(0,150));
        }
        return new ResultResponse()
                .setMessage(message.toString())
                .setCode(code)
                .setData(data);
    }

    /**
     * 先根据id和oldPassword查询数据库中是否存在原本的该用户，如果存在则再根据token中的uuid验证是否和数据库中用户的uuid匹配，
     * 如果匹配则代表是本用户，否则不是本用户。如果是本用户则允许验证新密码和旧密码是否相同，如果项目则不允许修改，否则允许修改。
     *
     * @param token
     * @param voUserUpdatePassword
     * @return
     */
    @CrudLog("修改自己的密码")
    @PostMapping("/updatePassword")
    public ResultResponse updatePassword(@RequestHeader("X-Token")String token,
                                         @RequestBody VoUserUpdatePassword voUserUpdatePassword){
        StringBuffer message = new StringBuffer();
        Object data = false;
        int code = 20001;
        User dbUser = userService.selectUserByIdAndPassword(voUserUpdatePassword);
        if (dbUser!=null){//用户存在
            String tokenUuid = JWTUtils.getTokenInfo(token).getClaim("uuid").asString();
            if (tokenUuid.equals(dbUser.getUuid())){//如果token中的uuid与要修改的用户的uuid一致说明是本人
                if (dbUser.getPassword().equals(voUserUpdatePassword.getNewPassword())){//如果新密码与旧密码一致
                    message.append("新密码不能与旧密码一致！");
                } else {//新旧密码不一致
                    try { //尝试捕获修改的异常
                        message.append("修改密码");
                        data = userService.updateUserPassword(voUserUpdatePassword);
                        code = 20000;
                    } catch (Exception e){
                        e.printStackTrace();
                        message.append(e.getMessage().substring(0,150));
                    }
                }
            } else {//不是本人
                message.append("只能修改自己的密码！");
            }
        } else {//用户不存在
            message.append("用户不存在或者旧密码错误！");
        }
        return new ResultResponse()
                .setMessage(message.toString())
                .setCode(code)
                .setData(data);
    }

    /**
     * 修改个人的基本信息：
     *  从header中获取token，然后从token中获取uuid，然后将传入的id查出来的dbUser的uuid进行比对，如果一致则代表是本用户，
     *
     * @param token
     * @param voUserUpdate
     * @return
     */
    @CrudLog("修改自己的个人信息")
    @PostMapping("/updateInfo")
    public ResultResponse update(@RequestHeader("X-Token")String token,
                                 @RequestBody VoUserUpdate voUserUpdate){
        Object data = false;
        StringBuffer message = new StringBuffer();
        int code = 20001;
        String tokenUuid = JWTUtils.getTokenInfo(token).getClaim("uuid").asString(); //从token中获取uuid
        User dbUser = userService.selectUserById(voUserUpdate.getId());
        if (dbUser!=null){ //根据id查询如果存在该用户
            if (dbUser.getUuid().equals(tokenUuid)){ //并且查询出来的uuid与token中的uuid匹配（说明就是本用户）
                try { //捕获修改用户的异常
                    data = userService.updateUserInfo(voUserUpdate);
                    message.append("修改个人信息");
                    code = 20000;
                } catch (Exception e){ //修改异常
                    e.printStackTrace();
                    message.append(e.getMessage().substring(0,150));
                }
            } else { //不是本用户
                message.append("只能修改自己的个人信息！");
            }
        } else {
            message.append("数据库中不存在该用户!");
        }
        return new ResultResponse()
                .setCode(code)
                .setMessage(message.toString())
                .setData(data);
    }
}
