package com.deway.blog.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;


@Repository
@Getter
public class AuthTokenConfig {

    @Value("${spring.authorization}")
    private boolean authorization;

    @Value("${spring.session-expire}")
    private Long sessionExpire;




}
