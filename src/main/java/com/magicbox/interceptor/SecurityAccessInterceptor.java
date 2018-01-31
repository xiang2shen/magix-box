package com.magicbox.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.magicbox.base.utilities.MD5Utils;

/**
 * 微信接口拦截器
 * 
 * @author xiangshuo
 *
 */
@Component
public class SecurityAccessInterceptor implements HandlerInterceptor {
	
	public static final String SECRET_KEY = "be19ece6-54f6-4433-b71c-e7d512c019d3";	// 签名秘钥
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String timestamp = request.getParameter("timestamp");
		String sign = request.getParameter("sign");
		
		if (StringUtils.isBlank(timestamp) || StringUtils.isBlank(sign)) {
			return false;
		}
		
		if (! sign.equalsIgnoreCase(MD5Utils.encrypt(timestamp + SECRET_KEY))) {
			return false;
		}
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
			
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
}
