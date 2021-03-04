package com.blctek.userserver.aop;

import com.blctek.commonserver.response.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/04/14:55
 * @Description:
 */
@Component
@Slf4j
public class LogAspect {

    private String clientName;

    private String methodName;

    /**
     * 匹配com.blctek.userserver.controller包下AuthController类里的log*（通配符）方法
     */
    @Pointcut("execution(public * com.blctek.userserver.controller.AuthController.log*(..))")//切入点描述 这个是controller包的切入点
    public void logPointCut(){}//签名，可以理解成这个切入点的一个名称

    /**
     * 前置通知：用于获取执行切点的方法名，类名以及传入的参数
     * @param joinPoint
     */
    @Before("logPointCut()")
    public void beforeLog(JoinPoint joinPoint){
//        System.out.println("前置通知 start---------------------");
        //String className = joinPoint.getTarget().getClass().getName();//执行切点的类名
        methodName = joinPoint.getSignature().getName();//执行切点的方法名
        Object[] parameters = joinPoint.getArgs();//执行切面的参数列表
        clientName = (String) parameters[0];//第一个参数clientName
//        System.out.println("前置通知 end---------------------");
    }

    @Around("logPointCut()")
    public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("环绕通知 start---------------------");
        Object proceed = pjp.proceed();
        ResultResponse res = (ResultResponse) proceed;
        System.out.println(res.getData());
        System.out.println("环绕通知 end---------------------");
        return proceed;
    }
}
