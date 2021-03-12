package com.blctek.userserver.aop;

import com.blctek.commonserver.response.ResultResponse;
import com.blctek.commonserver.utils.JWTUtils;
import com.blctek.commonserver.pojo.Logger;
import com.blctek.commonserver.service.LoggerService;
import com.blctek.userserver.anno.AuthLog;
import com.blctek.userserver.vo.VoToken;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

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
public class AuthAspect {

    @Autowired
    private LoggerService loggerService;

    //匹配加了AuthLog注解的方法
    @Pointcut("@annotation(com.blctek.userserver.anno.AuthLog)")
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
        ResultResponse resultResponse = (ResultResponse) returnValue;
        VoToken voToken = (VoToken)resultResponse.getData();
        if (voToken!=null){//登录成功或退出成功返回值中会有一个VoToken对象且不为null
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            AuthLog annotation = signature.getMethod().getAnnotation(AuthLog.class);
            String methodName = annotation.value();//从方法上的注解获取操作名
            Date time = new Date();//当前时间作为操作时间
            String token = voToken.getToken();
            String executor = JWTUtils.getTokenInfo(token).getClaim("name").asString();//从token中获得executor
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();//从解析器里获得request请求
            String ip = request.getRemoteAddr();//从request中获得ip
            String client = request.getHeader("X-Client");
            insertLogger(methodName,time,executor,ip,client);
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
