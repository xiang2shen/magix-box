package com.magicbox.exception;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.magicbox.base.support.ResponseWrapper;

/**
 * 全局异常处理
 * 
 * @author xiangshuo
 *
 */
public class GlobalExceptionResolver implements HandlerExceptionResolver {
	
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionResolver.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		try {
			OutputStream os = response.getOutputStream();
			new ObjectMapper().writeValue(os, ResponseWrapper.fail());
		} catch (IOException e) {
			logger.error("全局异常处理失败", e);
		}
		return null;
	}

}
