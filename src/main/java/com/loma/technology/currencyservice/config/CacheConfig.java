package com.loma.technology.currencyservice.config;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

/**
 * CacheConfig sets up Redis as the caching mechanism for the application.
 * It defines cache settings including default TTL (time-to-live) and null-value handling.
 */
@Configuration
public class CacheConfig {

	/**
     * Configures and returns a RedisCacheManager bean.
     * This manager handles caching operations using Redis as the backing store.
     *
     * @param connectionFactory the Redis connection factory (auto-configured by Spring Boot)
     * @return RedisCacheManager instance with custom configuration
     */
    @Bean
    RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
    	
    	// Define cache configuration:
        // - Set default TTL (time-to-live) for cache entries to 1 minute
        // - Do not cache null values
		RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
					.entryTtl(Duration.ofMinutes(1)) // Time-to-live per cache entry
					.disableCachingNullValues();  // Prevent caching of null results
		
		// Build the RedisCacheManager with the given connection factory and configuration
		return RedisCacheManager.builder(connectionFactory)
				.cacheDefaults(cacheConfiguration)
				.build();
	}

}
