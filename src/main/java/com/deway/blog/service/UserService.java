package com.deway.blog.service;

import com.deway.blog.entiry.auth.User;

public interface UserService {

    boolean register(User user);

    boolean exist(String userId);

    boolean login(User user);

}
