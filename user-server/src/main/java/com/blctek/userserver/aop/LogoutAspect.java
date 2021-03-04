package com.blctek.userserver.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/04/18:13
 * @Description:
 */
@Component
@Aspect
@Slf4j
public class LogoutAspect {

    /**
     * 匹配com.blctek.userserver.controller包下AuthController类里的logout方法
     */
    @Pointcut("execution(public * com.blctek.userserver.controller.AuthController.logout(..))")//切入点描述 这个是controller包的切入点
    public void logoutPointCut(){}//签名，可以理解成这个切入点的一个名称
}
