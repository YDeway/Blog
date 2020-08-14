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
    private boolean authorization;

    @Value("${spring.auth.session-expire}")
    private int sessionExpire;

    @Value("${spring.auth.multi-sign-on}")
    private boolean multiSignOn;


}
