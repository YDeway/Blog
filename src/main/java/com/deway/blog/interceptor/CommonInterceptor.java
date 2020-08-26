package com.deway.blog.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CommonInterceptor implements Interceptor{


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        return true;
    }

    @Override
    public void afterRegistry(InterceptorRegistration registration) {
        registration
            .addPathPatterns("/**");
    }
}
