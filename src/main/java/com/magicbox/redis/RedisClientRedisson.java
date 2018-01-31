package com.magicbox.redis;

import java.util.concurrent.TimeUnit;

import org.redisson.Redisson;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RLock;
import org.redisson.api.RMapCache;
import org.redisson.api.RQueue;
import org.redisson.api.RedissonClient;
import org.redisson.codec.MsgPackJacksonCodec;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RedisClientRedisson implements RedisClient {
	
	private static final Logger logger = LoggerFactory.getLogger(RedisClientRedisson.class);

	private String systemId;
	private String masterAddress;
	private String password;
	
	private int threads = 2;	// 线程数（cpu核数*2）
	
	private int masterConnectionMinimumIdleSize = 10;
	private int masterConnectionPoolSize = 64;
	private int idleConnectionTimeout = 30000;
	private int connectTimeout = 5000;
	private int retryAttempts = 2;
	private int retryInterval = 1000;
	private int reconnectionTimeout = 3000;
	private int failedAttempts = 3;
	
	private RedissonClient redisson;
	
	protected void init() {
		Config config = new Config();
		
		config
		.setCodec(new MsgPackJacksonCodec())
		.setThreads(threads)
		.setNettyThreads(threads)
		.useSingleServer()
		.setConnectTimeout(connectTimeout)
		.setFailedAttempts(failedAttempts)
		.setIdleConnectionTimeout(idleConnectionTimeout)
		.setPassword(password)
		.setReconnectionTimeout(reconnectionTimeout)
		.setRetryAttempts(retryAttempts)
		.setRetryInterval(retryInterval)
		.setAddress(masterAddress)
		.setConnectionPoolSize(masterConnectionPoolSize)
		.setConnectionMinimumIdleSize(masterConnectionMinimumIdleSize);

		redisson = Redisson.create(config);
	}
	
	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public String getMasterAddress() {
		return masterAddress;
	}

	public void setMasterAddress(String masterAddress) {
		this.masterAddress = masterAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getThreads() {
		return threads;
	}

	public void setThreads(int threads) {
		this.threads = threads;
	}
	
	private RMapCache<String, Object> getCacheMap() {
		return redisson.getMapCache(systemId);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(String key) {
		return (T) getCacheMap().get(key);
	}
	
	@Override
	public boolean containsKey(String key) {
		return getCacheMap().containsKey(key);
	}

	@Override
	public void put(String key, Object value) {
		put(key, value, 0);
	}
	
	@Override
	public void put(String key, Object value, long seconds) {
		getCacheMap().put(key, value, seconds, TimeUnit.SECONDS);
	}
	
	@Override
	public void putIfAbsent(String key, Object value) {
		putIfAbsent(key, value, 0);
	}
	
	@Override
	public void putIfAbsent(String key, Object value, long seconds) {
		getCacheMap().putIfAbsent(key, value, seconds, TimeUnit.SECONDS);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T remove(String key) {
		return (T) getCacheMap().remove(key);
	}
	
	private RLock getLock(String key) {
		return redisson.getLock(systemId + ":" + key);
	}

	@Override
	public boolean lock(String key) {
		return lock(key, 30);
	}
	
	@Override
	public boolean lock(String key, long leaseSeconds) {
		RLock lock = getLock(key);
		try {
			return lock.tryLock(2, leaseSeconds, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			logger.error("获取锁失败", e);
		}
		return false;
	}
	
	@Override
	public boolean lock(String key, long waitSeconds, long leaseSeconds) {
		RLock lock = getLock(key);
		try {
			return lock.tryLock(waitSeconds, leaseSeconds, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			logger.error("获取锁失败", e);
		}
		return false;
	}

	@Override
	public void unlock(String key) {
		try {
			RLock lock = getLock(key);
			lock.unlock();
		} catch (Exception e) {
			logger.error("redis解锁失败", e);
		}
	}
	
	@Override
	public long getAndIncrement(String key) {
		RAtomicLong atomicLong = redisson.getAtomicLong(systemId + ":" + key);
		return atomicLong.incrementAndGet();
	}
	
	@Override
	public void deleteIncrement(String key) {
		redisson.getAtomicLong(systemId + ":" + key).delete();
	}

	@Override
	public <T> T pop(String key) {
		RQueue<T> queue = redisson.getQueue(systemId + ":" + key);
		return queue.poll();
	}
	
	@Override
	public <T> boolean push(String key, T elem) {
		RQueue<T> queue = redisson.getQueue(systemId + ":" + key);
		return queue.add(elem);
	}
	
	@Override
	public int queueSize(String key) {
		return redisson.getQueue(systemId + ":" + key).size();
	}
	
	@Override
	public boolean isEmptyQueue(String key) {
		return redisson.getQueue(systemId + ":" + key).isEmpty();
	}
}
