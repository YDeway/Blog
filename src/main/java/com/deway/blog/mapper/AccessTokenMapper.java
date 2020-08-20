package com.deway.blog.mapper;

import com.deway.blog.config.datasource.DynamicDataSource;
import com.deway.blog.config.datasource.TargetDataSource;

@TargetDataSource(dataSource = DynamicDataSource.USER_DATASOURCE)
public interface AccessTokenMapper {


}