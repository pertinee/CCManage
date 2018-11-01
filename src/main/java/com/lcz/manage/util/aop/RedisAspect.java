package com.lcz.manage.util.aop;

import com.lcz.manage.util.exception.CCException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Redis切面处理类
 *
 * @author luchunzhou
 */
@Aspect
@Component
public class RedisAspect {
    private static final Logger logger = LoggerFactory.getLogger(RedisAspect.class);
    //是否开启redis缓存  true开启   false关闭
    @Value("${cc.redis.open: false}")
    private boolean open;

    @Around("execution(* com.lcz.manage.util.redis.RedisUtils.*(..))")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result = null;
        if(open){
            try{
                result = point.proceed();
            }catch (Exception e){
                logger.error("redis error", e);
                throw new CCException("Redis服务异常");
            }
        }
        return result;
    }
}
