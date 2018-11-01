package com.lcz.manage.util.aop;

import com.alibaba.fastjson.JSON;
import com.lcz.manage.sys.bean.SysLogBean;
import com.lcz.manage.sys.service.SysLogService;
import com.lcz.manage.util.HttpContextUtils;
import com.lcz.manage.util.IPUtils;
import com.lcz.manage.util.ShiroUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 说明: 系统业务异常切面处理类
 *
 * @author lcz <lucz25053@hsjry.com>
 * @version CFM-V1.0
 * @date 2018年10月22日10:10:02
 */
@Aspect
@Component
public class  SysBusiExpAspect {
    private static final Logger logger = Logger.getLogger(SysBusiExpAspect.class);

    @Autowired
    private SysLogService sysLogService;


    @Pointcut("execution(* com.lcz.manage.*.service.*.*(..))")
    private void pointcut() {
    }

    //Before增强：在目标方法被执行的时候织入增强
    //匹配com.lcz.manage.*.service包下面的所有类的所有方法的执行作为切入点
    @Before("execution(* com.lcz.manage.*.service.*.*(..))")
    public void before(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getName();//切入方法所属类名
        String methodName = joinPoint.getSignature().getName();//切入的方法名
        Object[] params = joinPoint.getArgs();//目标方法传入的参数
        StringBuilder log = new StringBuilder();
        log.append("[-------- BEFORE --------]:").append("[类名]:").append(className).append(",[方法名]:").append(methodName);
        if(null != params && params.length > 0){
            log.append(",[参数]:");
            for (Object par : params) {
                log.append(JSON.toJSONString(par) + ",");
            }
            log.substring(0,log.lastIndexOf(","));
        }
        logger.info(log.toString());
    }

    //AfterReturning增强：在目标方法正常完成后被织入
    //rvt是目标方法的返回值
    @AfterReturning(value = "pointcut()", returning = "returnObj")
    public void afterReturn(Object returnObj) {
        String result = JSON.toJSONString(returnObj);
        logger.info("[-------- afterReturning --------]:" + result);
    }

    //AfterThrowing增强：处理程序中未处理的异常
    //ex是目标方法拋出的异常
    @AfterThrowing(value = "execution(* com.lcz.manage.*.service.*.*(..))", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Throwable e) {
        SysLogBean sysLog = new SysLogBean();

        String className = joinPoint.getTarget().getClass().getName(); //切入方法所属类名
        String methodName = joinPoint.getSignature().getName(); //切入的方法名
        Object[] params = joinPoint.getArgs(); //目标方法传入的参数
        StringBuilder log = new StringBuilder();
        log.append("[-------- afterThrowing --------]:").append("[类名]:").append(className).append(",[方法名]:").append(methodName);
        StringBuilder paraSb = new StringBuilder();
        if(null != params && params.length > 0){
            paraSb.append("[参数]:");
            for(Object p : params){
                paraSb.append(p).append(",");
            }
            paraSb.substring(0,paraSb.lastIndexOf(","));
            log.append(paraSb);
        }
        log.append("[异常]:").append(e.getMessage());
        logger.error(log);

        //保存系统业务异常日志
        sysLog.setOperation("系统业务异常");
        sysLog.setMethod(className + "." + methodName + "()");
        sysLog.setParams(paraSb.toString());
        sysLog.setIp(IPUtils.getIpAddr(HttpContextUtils.getHttpServletRequest()));
        sysLog.setUsername(ShiroUtils.getUser().getUsername());
        sysLog.setCreateDate(new Date());
        sysLogService.save(sysLog);
    }

}
