package com.blctek.userserver.aop;

import com.blctek.commonserver.response.ResultResponse;
import com.blctek.userserver.vo.VoLogin;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
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
public class LoginAspect {

    /**
     * 匹配com.blctek.userserver.controller包下AuthController类里的login方法
     */
    @Pointcut("execution(public * com.blctek.userserver.controller.AuthController.login(..))")//切入点描述 这个是controller包的切入点
    public void loginPointCut(){}//签名，可以理解成这个切入点的一个名称


    /**
     * 前置通知：用于获取执行切点的方法名，类名以及传入的参数
     * @param joinPoint
     */
    /*@Before("loginPointCut()")
    public void beforeLog(JoinPoint joinPoint){
        //System.out.println("前置通知 start---------------------");
        //String className = joinPoint.getTarget().getClass().getName();//执行切点的类名
        //String methodName = joinPoint.getSignature().getName();//执行切点的方法名
        Object[] parameters = joinPoint.getArgs();//执行切面的参数列表
        clientName = (String) parameters[0];//第一个参数clientName
        VoLogin voLogin = (VoLogin) parameters[1];//第二个参数voLogin
        username = voLogin.getUsername();//将voLogin里的username提高作用域
        //System.out.println("前置通知 end---------------------");
    }*/

    /*@Around("loginPointCut()")
    public Object Around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println(clientName);
        System.out.println(username);
        Object proceed = pjp.proceed();
        System.out.println(proceed);
        return proceed;
    }*/

    /**
     * 正常返回通知
     * @param joinPoint 连接点
     * @param keys  正常返回的返回值
     */
    @AfterReturning(value = "loginPointCut()",returning = "keys")
    public void afterLogin(JoinPoint joinPoint,Object keys){
        //String className = joinPoint.getTarget().getClass().getName(); //切点类名
        String methodName = joinPoint.getSignature().getName(); //切点方法名
        Object[] args = joinPoint.getArgs();
        String clientName = (String) args[0]; //切点方法的第一个参数
        VoLogin voLogin = (VoLogin) args[1];  //切点方法的第二个参数
        String username = voLogin.getUsername();
        System.out.println(methodName+clientName+username);
        ResultResponse res = (ResultResponse) keys; //返回值强转为ResultResponse
        System.out.println(res);
    }

}
