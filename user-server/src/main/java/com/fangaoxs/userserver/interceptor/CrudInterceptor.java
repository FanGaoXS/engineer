package com.fangaoxs.userserver.interceptor;

import com.fangaoxs.commonserver.utils.JWTUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/12/13:18
 * @Description:    对于controller的增删改的拦截器
 */
@Component
public class CrudInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        String token = request.getHeader("X-Token");//从header中获取token
        try {//验证token有效性并且取出role
            String role = JWTUtils.getTokenInfo(token).getClaim("role").asString();//经过JWT取出role
            //System.out.println("role = " + role);
            List<String> roleList = new ArrayList<>();
            roleList.add("admin");
            roleList.add("developer");
            return roleList.contains(role); //判断角色是否在允许范围内
        } catch (RuntimeException e){ //token异常
            e.printStackTrace();
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print("token异常："+e.getMessage());
        }
        return false;
    }
}
