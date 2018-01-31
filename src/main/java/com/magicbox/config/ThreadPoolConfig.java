package com.magicbox.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 定时任务配置
 * 
 * @author xiangshuo
 *
 */
@Configuration
public class ThreadPoolConfig {

	@Bean
	public ExecutorService executorService() {
		return new ThreadPoolExecutor(10, 30, 5, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>());
	}
	
}
