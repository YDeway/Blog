package com.deway.blog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

/**
 * @author Deway
 */

@Configuration
public class RedisConfig {

    @Bean
    public Jedis jedis(@Value("${spring.redis.host}")String host, @Value("${spring.redis.port}")int port) {
        var jedis = new Jedis(host, port);
        System.out.println(jedis.ping());
        return jedis;
    }
}
