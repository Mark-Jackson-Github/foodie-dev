package com.imooc.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ServiceLogAspect {

    @Around("execution(* com.imooc.controller..*.*(..))")
    public Object logServiceMethodAccess(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object object = joinPoint.proceed();
        long end = System.currentTimeMillis();
        long t = end - start;
        //类名joinPoint.getTarget().getClass()
        //方法名joinPoint.getSignature().getName()
//        if(t>=3000){
//            log.error("========{}#{} 执行结束，耗时:{} 毫秒 ========",joinPoint.getTarget().getClass(),joinPoint.getSignature().getName(),t);
//        }else if (t>=2000){
//            log.warn("========{}#{} 执行结束，耗时:{} 毫秒 ========",joinPoint.getTarget().getClass(),joinPoint.getSignature().getName(),t);
//        }else {
//            log.info("========{}#{} 执行结束，耗时:{} 毫秒 ========",joinPoint.getTarget().getClass(),joinPoint.getSignature().getName(),t);
//        }
        return object;
    }

}
