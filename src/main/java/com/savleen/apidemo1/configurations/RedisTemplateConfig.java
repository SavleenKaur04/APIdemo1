package com.savleen.apidemo1.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisTemplateConfig {
    @Bean
    public RedisTemplate<String, Object> redisTemplate(
            RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String, Object> redistemplate=new RedisTemplate<>();
        redistemplate.setConnectionFactory(redisConnectionFactory);

        return redistemplate;
    }
}
