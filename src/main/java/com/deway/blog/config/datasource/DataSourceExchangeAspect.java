package com.deway.blog.config.datasource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 程序运行时实时切换Mapper对应的数据源,应该将注解放到Mapper上
 *
 * @author deway
 */
@Aspect
@Component
public class DataSourceExchangeAspect {

    @Pointcut("execution(* com.deway.blog.mapper..*Mapper.*(..))")
    public void pointCut(){}

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        var interfaces = point.getTarget().getClass().getInterfaces();
        for(var itfe : interfaces) {
            TargetDataSource annotation = itfe.getAnnotation(TargetDataSource.class);
            if(annotation != null) {
                DynamicDataSource.setDataSource(annotation.dataSource());
                return point.proceed();
            }
        }
        throw new Exception("Mapper needs to be annotated by TargetDataSource!");
    }


}
