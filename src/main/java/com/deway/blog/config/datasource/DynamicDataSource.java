package com.deway.blog.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

/**
 * 动态选择数据源，不过数据源都写死在代码里了，不过根据我现在编的需求这个情况是正常的
 */
@Component
public class DynamicDataSource extends AbstractRoutingDataSource {

    public static final String USER_DATASOURCE = "userDataSource";
    public static final String BLOG_DATASOURCE = "blogDataSource";

    //数据源的key必须线程私有
    private static final ThreadLocal<String> KEY_OF_DATASOURCE = new ThreadLocal<>();

    @SuppressWarnings("all")
    public DynamicDataSource(final Map<String, DruidDataSource> dataSourceMap) {
        super.setDefaultTargetDataSource(null);
        super.setTargetDataSources(new HashMap<>() {
            {
                for (Entry<String, DruidDataSource> entry : dataSourceMap.entrySet()) {
                    put(entry.getKey(), entry.getValue());
                }
            }
        });

    }

    @Override
    public  Object determineCurrentLookupKey() {
        return KEY_OF_DATASOURCE.get();
    }

    public static void setDataSource(String key) {
        KEY_OF_DATASOURCE.set(key);
    }
}

@Configuration
@PropertySource(value = "classpath:/datasource.properties")
class DataSourceFactory {


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

