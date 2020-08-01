package com.deway.blog.mapper;

import com.deway.blog.config.datasource.DynamicDataSource;
import com.deway.blog.config.datasource.TargetDataSource;
import com.deway.blog.entiry.User;

@TargetDataSource(dataSource = DynamicDataSource.USER_DATASOURCE)
public interface UserMapper {
    int create(User user);
}
