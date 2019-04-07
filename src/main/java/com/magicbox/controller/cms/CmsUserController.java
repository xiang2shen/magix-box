package com.magicbox.controller.cms;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.magicbox.base.exception.ErrorCodes;
import com.magicbox.base.support.ResponseWrapper;
import com.magicbox.base.utilities.MD5Utils;
import com.magicbox.controller.BaseController;
import com.magicbox.model.User;
import com.magicbox.redis.RedisClient;
import com.magicbox.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("CMS用户API")
@RestController
@RequestMapping("/cms")
public class CmsUserController extends BaseController {
	
	@Autowired
	private RedisClient redisClient;
	@Autowired
	private UserService userService;
	
	private static final long SESSION_TTL = 30 * 24 * 60 * 60;	// session有效时间
	
	@ApiOperation("登录")
	@PostMapping("/login")
	public ResponseWrapper<String> login(
			@RequestParam String username, 
			@RequestParam String password
			) {
		
		User user = userService.selectOneByUsername(username);
		if (null == user) {
			return ResponseWrapper.fail(ErrorCodes.MEMBER_NOT_FOUND);
		}
		if (!MD5Utils.encrypt(password).equalsIgnoreCase(user.getPassword())) {
			return ResponseWrapper.fail(ErrorCodes.PASSWORD_ERROR);
		}
		String token = genToken(user);
		
		return ResponseWrapper.succeed(token);
	}
	
	private String genToken(User user) {
		String token = UUID.randomUUID().toString();
		
		redisClient.put(getCmsSessionCacheKey(token), user.getUserId(), SESSION_TTL);
		
		return token;
	}
	
}
