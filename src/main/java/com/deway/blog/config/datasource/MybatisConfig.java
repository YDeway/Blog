package com.deway.blog.config.datasource;

import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import java.util.Properties;

/**
 * @todo 到时候将所有的配置外置到配置文件中
 *
 * @author Deway
 */
@Configuration
public class MybatisConfig {

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        var mapScanner = new MapperScannerConfigurer();
        mapScanner.setBasePackage("com.deway.blog.mapper");
        return mapScanner;
    }

    @Bean
    public SqlSessionFactoryBean userSqlSessionFactoryBean(DynamicDataSource dataSource, Interceptor[] interceptors) throws Exception {

        var resourceResolver = new PathMatchingResourcePatternResolver();

        var sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setMapperLocations(resourceResolver.getResources("classpath:/mapper/*Mapper.xml"));
        sqlSessionFactory.setConfigLocation(resourceResolver.getResource("classpath:mybatis.xml"));
        sqlSessionFactory.setDataSource(dataSource);
        sqlSessionFactory.setPlugins(interceptors);

        sqlSessionFactory.afterPropertiesSet();

        return sqlSessionFactory;
    }

    @Bean
    public PageInterceptor pageHelper(@Value("${spring.pagehelper.database}")String database) {
        var pageInterceptor = new PageInterceptor();

        var props = new Properties();
        props.setProperty("helperDialect", database);
        props.setProperty("reasonable", "true");

        pageInterceptor.setProperties(props);

        return pageInterceptor;
    }

}
