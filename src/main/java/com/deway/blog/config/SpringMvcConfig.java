package com.deway.blog.config;

import com.deway.blog.interceptor.Interceptor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;
import java.nio.charset.StandardCharsets;
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

    /**
     * 修改容器默认的text/plain转化器，设置UTF-8编码<br>
     * 这里似乎是将响应发送时最后一步的转换？也就是Response被提交之前或者执行getWriter()或者 getOutputStream()之前的最后一步，
     * 也就是过滤器虽然设置了UTF-8编码，但默认的文本转化器以默认的 StandardCharsets.ISO_8859_1再编码一次，就有问题了(仅作猜测)<br>
     * 区分servlet和spring mvc的字符编码，以及直接response.getOutputStream()写入数据的编码<br>
     * @see WebMvcConfigurationSupport  添加了默认的消息处理器（WebMvcConfigurationSupport）
     * @param converters 容器提供的消息转换器队列
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.forEach(e -> {
            if (e instanceof StringHttpMessageConverter) {
                ((StringHttpMessageConverter) e).setDefaultCharset(StandardCharsets.UTF_8);
            }
        });
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST")
                .allowedHeaders("*")
                .exposedHeaders("Access-Control-Allow-Origin")
                .allowCredentials(true);
    }

}
