package com.deway.blog.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

/**
 * 这个坑定是有的问题的
 */
@Component
public class DynamicDataSource extends AbstractRoutingDataSource {

    public static final String USER_DATASOURCE = "userDataSource";
    public static final String BLOG_DATASOURCE = "blogDataSource";

    private static String dataSource;

    @Override
    public Object determineCurrentLookupKey() {
        return dataSource;
    }

    public static void setDataSource(String dataSource) {
        DynamicDataSource.dataSource = dataSource;
    }

    public DynamicDataSource(final Map<String, DruidDataSource> dataSourceMap) {
        super.setDefaultTargetDataSource(null);
        super.setTargetDataSources(new HashMap<>() {
            {
                for (Entry entry : dataSourceMap.entrySet()) {
                    put(entry.getKey(), entry.getValue());
                }
            }
        });

    }
}

@Configuration
@PropertySource(value = "classpath:/datasource.properties")
class DataSource {

    @Bean(DynamicDataSource.BLOG_DATASOURCE)
    public DruidDataSource blogDataSource(@Value("${datasource.mariaDB.blog.username}")String username,
                                          @Value("${datasource.mariaDB.blog.password}")String password,
                                          @Value("${datasource.mariaDB.blog.url}")String url,
                                          @Value("${datasource.mariaDB.blog.driver-class-name}")String driver) {
        var dataSource = new DruidDataSource();
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driver);
        return dataSource;
    }



    //    @Value("#{datasource.mariaDB.user}")
    @Bean(DynamicDataSource.USER_DATASOURCE)
    public DruidDataSource userDataSource(@Value("${datasource.mariaDB.user.username}")String username,
                                          @Value("${datasource.mariaDB.user.password}")String password,
                                          @Value("${datasource.mariaDB.user.url}")String url,
                                          @Value("${datasource.mariaDB.user.driver-class-name}")String driver) {
        var dataSource = new DruidDataSource();
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driver);
        return dataSource;
    }
}

