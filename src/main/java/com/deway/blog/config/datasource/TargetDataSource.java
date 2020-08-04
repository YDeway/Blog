package com.deway.blog.config.datasource;

import java.lang.annotation.*;

/**
 * 标识Mapper使用的数据源
 *
 * @author Deway
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TargetDataSource {

    String dataSource();

}
