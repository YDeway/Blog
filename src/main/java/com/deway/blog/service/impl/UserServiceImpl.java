package com.deway.blog.service.impl;

import com.deway.blog.entiry.User;
import com.deway.blog.mapper.UserMapper;
import com.deway.blog.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;

    @Override
    public boolean register(User user) {
        System.out.println(user.toString());
        return userMapper.create(user) > 0;
    }
}
