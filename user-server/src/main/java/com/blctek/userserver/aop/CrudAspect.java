package com.blctek.userserver.aop;

import com.blctek.commonserver.pojo.Logger;
import com.blctek.commonserver.response.ResultResponse;
import com.blctek.commonserver.utils.JWTUtils;
import com.blctek.userserver.anno.CrudLog;
import com.blctek.userserver.service.LoggerService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
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
 * @Date: 2021/03/12/16:14
 * @Description:    对加上CrudLog注解的方法进行日志aop：增删改进行打印并且输入到日志表中
 */
@Component
@Slf4j
@Aspect
public class CrudAspect {
    @Autowired
    private LoggerService loggerService;

    /**
     * 定义切点为：CrudLog这个注释
     */
    @Pointcut("@annotation(com.blctek.userserver.anno.CrudLog)")
    public void crudPointCut() {
    }//切入点签名


    /**
     * 正常返回通知（方法有正常返回值时）：（一般用这个）
     * 在整个后置通知前，在整个前置通知后，与异常返回并列。
     * 将返回值强转为ResultResponse，获得其中的data，如果data为true则证明操作成功，
     * 再从切点里获得注解名称（也就是操作名），从handler中获得request再从request中获得ip，
     * 再从header中根据X-Client和X-Token获得客户端名称和token，再从token中获得name（executor操作者）
     *
     * @param joinPoint   切点
     * @param returnValue 正常返回值
     */
    @AfterReturning(pointcut = "crudPointCut()", returning = "returnValue")
    public void crudAfterReturning(JoinPoint joinPoint, Object returnValue) {
        ResultResponse resultResponse = (ResultResponse) returnValue; //将返回值强转为ResultResponse
        Boolean data = (Boolean)resultResponse.getData();             //获取ResultResponse里的data
        if (data){ //如果操作成功执行（操作成功会返回true）
            MethodSignature signature = (MethodSignature)joinPoint.getSignature();
            CrudLog annotation = signature.getMethod().getAnnotation(CrudLog.class);
            String methodName = annotation.value();//将注解上的值赋值为操作名
//            System.out.println("methodName = " + methodName);
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();//从解析器里获得request请求
            String ip = request.getRemoteAddr();//从request请求中获得ip地址
            String client = request.getHeader("X-Client");//从headers中取出客户端名称
            String token = request.getHeader("X-Token");//从headers中取出token
            Date time = new Date();//时间
            String executor = "未知";//执行者
            try {
                executor = JWTUtils.getTokenInfo(token).getClaim("name").asString();
            } catch (RuntimeException e){
                executor = "未知";
            }
            insertLogger(methodName,time,executor,ip,client);
        }

    }

    //操作名、操作时间、执行人、ip、来源客户端
    private void insertLogger(String name, Date time, String executor, String ip, String client){
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

