package com.deway.blog.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

/**
 * @author Deway
 */
@Slf4j
@Configuration
public class RedisConfig {

    @Bean
    public Jedis jedis(@Value("${spring.redis.host}")String host, @Value("${spring.redis.port}")int port) {
        Jedis jedis = null;
        try {
             jedis = new Jedis(host, port);
             System.out.println(jedis.ping());
        } catch (Throwable e) {
            System.err.println("redis created failed: " + e.getMessage());
        }
        return jedis;
    }
}
