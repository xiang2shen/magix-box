package com.magicbox.redis;

public final class RedisClientFactory {

	/**
	 * 获取redis客户端
	 * 
	 * @param systemId 系统标识
	 * @param masterAddress
	 * @param password
	 * @param threads
	 * @return
	 */
	public static RedisClient getClient(String systemId, String masterAddress, int threads) {
		return getClient(systemId, masterAddress, threads, null);
	}
	
	/**
	 * 获取redis客户端
	 * 
	 * @param systemId 系统标识
	 * @param masterAddress
	 * @param threads
	 * @param password
	 * @return
	 */
	public static RedisClient getClient(String systemId, String masterAddress, int threads, String password) {
		notBlank(systemId);
		notBlank(masterAddress);
		
		RedisClientRedisson client = new RedisClientRedisson();
		client.setSystemId(systemId);
		client.setMasterAddress(masterAddress);
		client.setPassword(password);
		client.setThreads(threads);
		
		client.init();
		
		return client;
	}
	
	private static void notBlank(String text) {
		if (null == text || text.length() == 0) {
			throw new IllegalArgumentException("创建redis客户端失败，入参错误");
		}
	}
}
