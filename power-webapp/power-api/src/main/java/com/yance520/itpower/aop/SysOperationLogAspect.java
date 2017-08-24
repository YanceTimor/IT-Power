package com.yance520.itpower.aop;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yance520.itpower.model.sys.SysOperationLog;
import com.yance520.itpower.service.sys.SysoperationLogService;
import com.yance520.itpower.utils.HttpContextUtils;
import com.yance520.itpower.utils.IPUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 系统日志，切面处理类
 */
@Aspect
@Component
public class SysOperationLogAspect {
    @Reference
    private SysoperationLogService sysoperationLogService;

    @Pointcut("@annotation(com.yance520.itpower.annotation.SysOperationLog)")
    public void logPointCut() {

    }

    @Before("logPointCut()")
    public void saveSysLog(JoinPoint joinPoint) throws Exception {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        //设置用户操作日志对象
        SysOperationLog sysLog = new SysOperationLog();
        sysLog.setStartTime(new Date());
        com.yance520.itpower.annotation.SysOperationLog sysOperationLog = method.getAnnotation(com.yance520.itpower.annotation.SysOperationLog.class);
        if (sysOperationLog != null) {
            //注解上的描述
            sysLog.setOperation(sysOperationLog.value());
        }

        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setRemark(className + "." + methodName + "()");

        //获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        sysLog.setMethod(request.getMethod());

        //请求的参数
        /*Object[] args = joinPoint.getArgs();
        String params = JSON.toJSONString(args[0]);*/
        sysLog.setParameter(HttpContextUtils.getParameterForLog(request));

        //设置IP地址
        sysLog.setIp(IPUtils.getIpAddr(request));
        sysLog.setUrl(request.getRequestURL().toString());

        //保存系统日志
        sysoperationLogService.SaveLog(sysLog);
    }

}
