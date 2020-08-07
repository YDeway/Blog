package com.deway.blog.config.datasource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
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
    public SqlSessionFactoryBean userSqlSessionFactoryBean(DynamicDataSource dataSource) throws Exception {

        var resourceResolver = new PathMatchingResourcePatternResolver();

        var sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setMapperLocations(resourceResolver.getResources("classpath:/mapper/*Mapper.xml"));
        sqlSessionFactory.setConfigLocation(resourceResolver.getResource("classpath:mybatis.xml"));
        sqlSessionFactory.setTypeAliasesPackage("com.deway.blog.entity");
        sqlSessionFactory.setDataSource(dataSource);

        sqlSessionFactory.afterPropertiesSet();

        return sqlSessionFactory;
    }

}
