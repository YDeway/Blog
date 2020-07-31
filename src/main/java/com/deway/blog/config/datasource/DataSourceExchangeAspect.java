package com.deway.blog.config.datasource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class DataSourceExchangeAspect {

    @Pointcut("execution(* com.deway.blog.service.impl.*(..))")
    public void pointCut(){}

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        var originObject = point.getTarget();
        var ann = originObject.getClass().getAnnotation(TargetDataSource.class);
        DynamicDataSource.setDataSource(ann.dataSource());
        return point.proceed();
    }


}
