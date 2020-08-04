package com.deway.blog.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 全局配置文件
 *
 * @author Deway
 */
@Configuration
@ComponentScan("com.deway.blog")
@EnableAspectJAutoProxy
public class GlobalConfig {


}
