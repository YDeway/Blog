package com.deway.blog.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;


/**
 * Controller的HandlerInterceptor扩展接口
 *
 * @author Deway
 */
public interface Interceptor extends HandlerInterceptor {

    /**
     * 每个Interceptor实现此方法指定匹配路径
     *
     * @param registration Interceptor注册返回的对象
     */
    void afterRegistry(InterceptorRegistration registration);

}
