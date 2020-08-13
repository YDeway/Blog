package com.deway.blog.config;

import com.deway.blog.interceptor.Interceptor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import java.util.List;

/**
 * MVC配置器
 *
 * @author Deway
 */

@EnableWebMvc
@Configuration
@AllArgsConstructor
public class SpringMvcConfig implements WebMvcConfigurer{

    private final List<Interceptor> interceptors;

    /**
     * @todo 配置文件外置
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations(
                "classpath:/static/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations(
                "classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations(
                "classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        interceptors.forEach(interceptor -> {
            var registration = registry.addInterceptor(interceptor);
            interceptor.afterRegistry(registration);
        });
    }


}
