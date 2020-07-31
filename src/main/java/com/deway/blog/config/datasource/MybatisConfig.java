package com.deway.blog.config.datasource;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import java.io.IOException;

@Configuration
//@MapperScan(basePackages = {"com.deway.blog.mapper"})
public class MybatisConfig {

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapScanner = new MapperScannerConfigurer();
        mapScanner.setBasePackage("com.deway.blog.mapper");
        return mapScanner;
    }

    @Bean
    public MybatisSqlSessionFactoryBean userSqlSessionFactoryBean(DynamicDataSource dataSource) throws IOException {
        var resourceResolver = new PathMatchingResourcePatternResolver();
        var mappers = resourceResolver.getResources("classpath:/mapper/*Mapper.xml");

        var sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setMapperLocations(mappers);
        sqlSessionFactory.setTypeAliasesPackage("com.deway.blog.entity");
        sqlSessionFactory.setDataSource(dataSource);

        return sqlSessionFactory;
    }

}
