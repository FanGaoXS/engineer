package com.fangaoxs.engineserver.interceptor;

import com.fangaoxs.commonserver.utils.JWTUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/12/13:18
 * @Description: 根据token和访问的client来限制是否能进行增删改查的操作：
 *              1、如果是来自upload-web的客户端直接放行，为null或为空则拦截
 *              2、从token中取出role角色，如果角色是admin或developer则放行
 */
@Component
public class CrudInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        String client = request.getHeader("X-Client");//从header中获取client
//        System.out.println("client = " + client);
        if (client==null||client.equals("")){//如果client为null则不放行
            return false;//不放行
        } else if (client.equals("upload-web")){//如果来自上传小程序则直接放行不进行身份验证的操作
            return true;//放行
        }
        String token = request.getHeader("X-Token");//从header中获取token
        try {//验证token有效性并且取出role
            String role = JWTUtils.getTokenInfo(token).getClaim("role").asString();//经过JWT取出role
            //System.out.println("role = " + role);
            if (role.equals("admin")||role.equals("developer")) {//如果身份是管理员或者开发者
                return true;//放行
            }
        } catch (RuntimeException e){ //token异常
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print("token异常："+e.getMessage());
            return false;//不放行
        }
        return false;
    }
}
