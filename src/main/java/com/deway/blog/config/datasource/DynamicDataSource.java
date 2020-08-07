package com.deway.blog.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

/**
 * 动态选择数据源，不过数据源都写死在代码里了，不过根据我现在编的需求这个情况是正常的
 *
 * @author Deway
 */
@Component
public class DynamicDataSource extends AbstractRoutingDataSource {

    public static final String USER_DATASOURCE = "userDataSource";
    public static final String BLOG_DATASOURCE = "blogDataSource";

    /**
     * 数据源的key必须线程私有
     */
    private static final ThreadLocal<String> KEY_OF_DATASOURCE = new ThreadLocal<>();

    @SuppressWarnings("all")
    public DynamicDataSource(final Map<String, DruidDataSource> dataSourceMap) {
        super.setDefaultTargetDataSource(null);
        super.setTargetDataSources(new HashMap<>() {
            {
                for (var entry : dataSourceMap.entrySet()) {
                    put(entry.getKey(), entry.getValue());
                }
            }
        });
        this.afterPropertiesSet();
    }

    @Override
    public  Object determineCurrentLookupKey() {
        return KEY_OF_DATASOURCE.get();
    }

    /**
     * <em>线程复用</em> 时，同一个线程会获取到当前线程上一次设置的数据，因此应该本次代码执行完成后应该移除当前代码设置的值，
     * 但是本项目暂时不会发生这种情况，因为每次获取值时会先设置值，但我还是写上
     */
    public static void removeDataSource() {
        KEY_OF_DATASOURCE.remove();
    }

    public static void setDataSource(String key) {
        KEY_OF_DATASOURCE.set(key);
    }
}

@Configuration
class DataSourceFactory {

    @Bean(DynamicDataSource.BLOG_DATASOURCE)

    public DruidDataSource blogDataSource(@Value("${spring.datasource.blog.username}")String username,
                                          @Value("${spring.datasource.blog.password}")String password,
                                          @Value("${spring.datasource.blog.url}")String url,
                                          @Value("${spring.datasource.blog.driver-class-name}")String driver) {
        var dataSource = new DruidDataSource();
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driver);
        return dataSource;
    }

    @Bean(DynamicDataSource.USER_DATASOURCE)
    public DruidDataSource userDataSource(@Value("${spring.datasource.user.username}")String username,
                                          @Value("${spring.datasource.user.password}")String password,
                                          @Value("${spring.datasource.user.url}")String url,
                                          @Value("${spring.datasource.user.driver-class-name}")String driver) {
        var dataSource = new DruidDataSource();
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driver);
        return dataSource;
    }



}

