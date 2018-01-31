package com.magicbox.redis;

/**
 * redis客户端
 * 
 * @author xiangshuo
 *
 */
public interface RedisClient {

	<T> T get(String key);
	
	boolean containsKey(String key);
	
	void put(String key, Object value);
	
	void put(String key, Object value, long seconds);
	
	void putIfAbsent(String key, Object value);
	
	void putIfAbsent(String key, Object value, long seconds);
	
	<T> T remove(String key);
	
	boolean lock(String key);
	
	boolean lock(String key, long leaseSeconds);
	
	boolean lock(String key, long waitSeconds, long leaseSeconds);
	
	void unlock(String key);

	long getAndIncrement(String key);

	void deleteIncrement(String key);

	<T> T pop(String key);

	<T> boolean push(String key, T elem);

	int queueSize(String key);

	boolean isEmptyQueue(String key);

}
