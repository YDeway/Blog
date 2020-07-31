package com.deway.blog.tool.config;

import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;
import java.io.IOException;

/**
 * 还没实现Spring MVC读取ＹＭＬ文档并直接注入对象或者Ｍａｐ或者Properties
 */
public class YMLParser implements PropertySourceFactory {

    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {


        return null;
    }
}
