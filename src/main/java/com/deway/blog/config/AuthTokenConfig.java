package com.deway.blog.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;


/**
 *
 *
 * @author Deway
 */
@Repository
@Getter
public class AuthTokenConfig {

    @Value("${spring.auth.authorization}")
    private boolean authorized;

    @Value("${spring.auth.session-expire}")
    private Integer sessionExpire;

    @Value("${spring.auth.multi-sign-on}")
    private boolean multiSignOn;


}
