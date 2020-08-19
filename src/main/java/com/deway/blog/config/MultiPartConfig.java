package com.deway.blog.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import java.util.List;

/**
 * 在使用注解@Configuration的类上，会使用到cglib技术实现代理，因此方法被代理的方法不能为private
 *
 * @author Deway
 */

@Getter
@Configuration
public class MultiPartConfig {

    //@todo 原生@Value不支持直接这么做
    @Value("${spring.upload.max-per-file}")
    private long maxPerFileSize;

    @Value("${spring.upload.path}")
    private String baseUploadPath;

    @Value("${spring.upload.allowed-type}")
    private List<String> allowedType;



    @Bean("multipartResolver")
    protected CommonsMultipartResolver multipartResolver() {
        var resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSizePerFile(this.maxPerFileSize);
        return resolver;
    }

}
