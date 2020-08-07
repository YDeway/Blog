package com.deway.blog.config;

import com.deway.blog.tool.config.PropertyParser;
import org.springframework.context.annotation.*;

/**
 * 全局配置文件
 *
 * @author Deway
 */
@Configuration
@ComponentScan("com.deway.blog")
@PropertySource(value = "classpath:/application.yml", factory = PropertyParser.class)
@EnableAspectJAutoProxy
public class GlobalConfig {

}
