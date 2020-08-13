package com.deway.blog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;


@Repository
public class AuthTokenConfig {

    @Value("${spring.authorization}")
    private boolean authorization;

    @Value("${spring.session-expire}")
    private long sessionExpire;




}
