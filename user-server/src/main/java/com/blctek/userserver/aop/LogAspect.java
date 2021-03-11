package com.blctek.userserver.aop;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.blctek.commonserver.response.ResultResponse;
import com.blctek.commonserver.utils.JWTUtils;
import com.blctek.userserver.pojo.Logger;
import com.blctek.userserver.service.LoggerService;
import com.blctek.userserver.vo.VoToken;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/04/14:55
 * @Description: 登录和退出操作的切面（增加登录或退出操作日志）
 */
@Component
@Slf4j
@Aspect
public class LogAspect {

    @Autowired
    private LoggerService loggerService;

    //匹配com.blctek.userserver.controller包下AuthController类里的log*（通配符）方法
    @Pointcut("execution(public * com.blctek.userserver.controller.AuthController.log*(..))")
    public void logPointCut(){}//签名，可以理解成这个切入点的一个名称

    /**
     * 正常返回通知：
     * 先从joinPoint切点里获取切点类名、切点方法名和切点传入参数列表，从returnValue里获取返回值
     * 并且得到返回值里的data对象，然后再根据切点方法名（login或logout）获取操作是登录还是退出
     * 登录时并且data不为空（说明成功登录）再获取data中的token，再解析token得到执行者的name
     * 退出时获取传参列表的第一个是token，再解析token得到执行者的name，最后再根据methodName得到
     * 对应的中文操作名，该操作执行的时间、该操作的执行人、执行该操作的ip、以及执行该操作的客户端
     * 并且从headers里获取X-Client的客户端名称
     * @param joinPoint     切点
     * @param returnValue   正常返回的返回值
     */
    @AfterReturning(pointcut = "logPointCut()", returning = "returnValue")
    public void afterReturningLog(JoinPoint joinPoint,Object returnValue){

        //从joinPoint切点和returnValue返回值里获取相关数据
        //String className = joinPoint.getTarget().getClass().getName(); //切点类名
        String methodName = joinPoint.getSignature().getName(); //切点方法名
        Object[] args = joinPoint.getArgs(); //切点传参列表
        ResultResponse response = (ResultResponse) returnValue; //将返回值强转为ResultResponse
        Object data = response.getData();//获取response里的data

        //新建方法名集合（用于比对传入的英文方法名映射到中文操作名上）
        HashMap<String, String> methodMap = new HashMap<>();
        methodMap.put("login","登录");
        methodMap.put("logout","退出");

        //从英文方法名对应到中文操作名
        String methodChineseName = methodMap.get(methodName);
        //利用Date对象生成当前时间
        Date time = new Date();
        //获取当前的请求的ip地址
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();//从解析器里获得request请求
        String ipAddress = request.getRemoteAddr();//从request请求中获得ip地址
        String clientName = request.getHeader("X-Client");//从请求头里获取X-Client
        switch (methodName){
            case "login"://切点方法是login
                if (data!=null){//返回有data（说明成功登录）
                    VoToken voToken = (VoToken) data; //从返回值里获取token
                    String token = voToken.getToken();
                    DecodedJWT decodedJWT = JWTUtils.getTokenInfo(token);
                    String name = decodedJWT.getClaim("name").asString();//从token获取name操作者的姓名
                    insertLogger(methodChineseName,time,name,ipAddress,clientName);
                }
                break;
            case "logout"://切点方法是logout
                VoToken voToken = (VoToken) args[0]; //从传参列表的第一个参数获取token
                String token = voToken.getToken();
                DecodedJWT decodedJWT = JWTUtils.getTokenInfo(token);
                String name = decodedJWT.getClaim("name").asString(); //从token中获取name操作者姓名
                insertLogger(methodChineseName,time,name,ipAddress,clientName);
                break;
        }
    }

    //操作名、操作时间、执行人、ip、来源客户端
    private void insertLogger(String name,Date time,String executor,String ip,String client){
        if (client==null){//如果client为null则从未知客户端操作
            client = "未知";
        }
        log.info("用户[{}]从[{}]客户端成功[{}]，时间是[{}]，ip地址是[{}]",
                executor,client,name,time.toLocaleString(),ip);
        Logger logger = new Logger();
        logger.setName(name);
        logger.setTime(time);
        logger.setExecutor(executor);
        logger.setIp(ip);
        logger.setClient(client);
        loggerService.insertLogger(logger);
    }
}
