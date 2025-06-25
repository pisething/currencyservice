package com.loma.technology.currencyservice.config;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

@Configuration
public class CacheConfig {

    @Bean
    RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
    	
    	
		RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
					.entryTtl(Duration.ofMinutes(1))
					.disableCachingNullValues();
		
		return RedisCacheManager.builder(connectionFactory)
				.cacheDefaults(cacheConfiguration)
				.build();
	}

}
