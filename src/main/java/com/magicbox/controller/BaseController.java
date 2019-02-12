package com.magicbox.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.magicbox.dto.SessionMember;
import com.magicbox.redis.RedisClient;

public abstract class BaseController {
	
	@Autowired
	private RedisClient redisClient;

	/**
	 * 获取当前用户
	 * 
	 * @param token
	 * @return
	 */
	protected SessionMember getMember(String token) {
		if (StringUtils.isBlank(token)) {
			return null;
		}
		
		SessionMember session = redisClient.get(getSessionCacheKey(token));
		return session;
	}
	
	protected String getSessionCacheKey(String token) {
		return "token:" + token;
	}
	
	protected String getCmsSessionCacheKey(String token) {
		return "cmstoken:" + token;
	}

	/**
	 * 获取当前用户ID
	 * 
	 * @param token
	 * @return
	 */
	protected Long getMemberId(String token) {
		SessionMember member = getMember(token);
		if (null != member) {
			return member.getMemberId();
		}

		return null;
	}
	
	/**
	 * 获取当前后台用户ID
	 * 
	 * @param token
	 * @return
	 */
	protected Long getUserId(String token) {
		if (StringUtils.isBlank(token)) {
			return null;
		}
		
		Long userId = redisClient.get(getCmsSessionCacheKey(token));
		return userId;
	}
}
