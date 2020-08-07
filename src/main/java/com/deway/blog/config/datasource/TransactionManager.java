package com.deway.blog.config.datasource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;

/**
 * @todo 能否一个TransactionManager管理多个数据源？对TransactionManager了解很少！
 *       多数据源操作在同一个事务里完成怎么做？
 *
 * @author Deway
 */
@Configuration
@EnableTransactionManagement
public class TransactionManager {

    public static final String USER_TRANSACTION = "userTransactionManager";
    public static final String BLOG_TRANSACTION = "blogTransactionManager";

    @Bean(USER_TRANSACTION)
    public DataSourceTransactionManager userTransactionManager(
            @Qualifier(DynamicDataSource.USER_DATASOURCE) DataSource dataSource) {

        var x = new DataSourceTransactionManager(dataSource);
        x.afterPropertiesSet();
        return x;
    }

    @Bean(BLOG_TRANSACTION)
    public DataSourceTransactionManager blogTransactionManager(
            @Qualifier(DynamicDataSource.BLOG_DATASOURCE) DataSource dataSource) {

        var x = new DataSourceTransactionManager(dataSource);
        x.afterPropertiesSet();
        return x;
    }

}