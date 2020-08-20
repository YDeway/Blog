package com.deway.blog.service;

import com.deway.blog.entity.auth.User;

public interface UserService {

    boolean create(User user) throws Exception;

    boolean exist(String userId);

    boolean login(User user);

}
