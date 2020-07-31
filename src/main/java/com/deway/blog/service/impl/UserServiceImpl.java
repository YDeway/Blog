package com.deway.blog.service.impl;

import com.deway.blog.config.datasource.DynamicDataSource;
import com.deway.blog.config.datasource.TargetDataSource;
import com.deway.blog.entiry.User;
import com.deway.blog.mapper.UserMapper;
import com.deway.blog.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@TargetDataSource(dataSource = DynamicDataSource.USER_DATASOURCE)
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;

    @Override
    public boolean register(User user) {
        return userMapper.create(user) > 0;
    }
}
