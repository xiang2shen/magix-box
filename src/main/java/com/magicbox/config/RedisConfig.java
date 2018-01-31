package com.magicbox.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.magicbox.redis.RedisClient;
import com.magicbox.redis.RedisClientFactory;


@Configuration
public class RedisConfig {
	
	@Value("${redis.systemId}")
	private String systemId;
	@Value("${redis.threads}")
	private int threads;
	@Value("${redis.masterAddress}")
	private String masterAddress;
	@Value("${redis.password}")
	private String password;
	
	@Bean
	public RedisClient redisClient() {
		return RedisClientFactory.getClient(systemId, masterAddress, threads, password);
	}
	
}
