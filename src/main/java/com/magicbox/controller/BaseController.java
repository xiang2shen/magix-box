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
	 * @param request
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

	/**
	 * 获取当前用户ID
	 * 
	 * @param request
	 * @return
	 */
	protected Long getMemberId(String token) {
		SessionMember member = getMember(token);
		if (null != member) {
			return member.getMemberId();
		}

		return null;
	}
	
}
