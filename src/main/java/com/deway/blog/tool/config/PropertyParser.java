package com.deway.blog.tool.config;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;
import java.io.IOException;
import java.util.Properties;

/**
 *  读取yaml、properties配置文件
 *
 * @author Deway
 */
public class PropertyParser implements PropertySourceFactory {

    public static final String YML_DOCUMENT_SUFFIX = ".yml";
    public static final String YAML_DOCUMENT_SUFFIX = ".yaml";
    public static final String PROPERTIES_DOCUMENT_SUFFIX = ".properties";


    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
        var sourceName = name != null ? name : resource.getResource().getFilename();
        if(!resource.getResource().exists() || sourceName == null) {
            throw new IOException("Resource is not existed");
        }
        if (sourceName.toLowerCase().endsWith(PROPERTIES_DOCUMENT_SUFFIX)) {
            return new PropertiesPropertySource(sourceName, new Properties());
        } else if(sourceName.toLowerCase().endsWith(YML_DOCUMENT_SUFFIX)
                || sourceName.toLowerCase().endsWith(YAML_DOCUMENT_SUFFIX)) {
            return new PropertiesPropertySource(sourceName, loadYml(resource));
        } else {
            throw new IOException("File type is not supported:" + sourceName);
        }
    }

    private Properties loadYml(EncodedResource resource) {
        var factory = new YamlPropertiesFactoryBean();
        factory.setResources(resource.getResource());
        factory.afterPropertiesSet();
        return factory.getObject();
    }
}
