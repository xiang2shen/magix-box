package com.magicbox.base.support;

import java.io.Serializable;

/**
 * 服务响应头部
 * 
 * @author xiangshuo
 *
 */
public interface ResponseHead extends Serializable {

	/**
	 * 获取响应码
	 * 
	 * @return
	 */
	String getCode();
	
	/**
	 * 获取响应消息
	 * 
	 * @return
	 */
	String getMsg();
	
	/**
	 * 成功响应
	 * 
	 */
	ResponseHead SUCCESS = new ResponseHead() {
		
		private static final long serialVersionUID = 1L;

		@Override
		public String getMsg() {
			return "成功";
		}
		
		@Override
		public String getCode() {
			return "200";
		}
		
		@Override
		public String toString() {
			return "SUCCESS " + getCode() + " " + getMsg();
		}
	};
	
	ResponseHead PARAM_ERROR = new ResponseHead() {
		
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getMsg() {
			return "入参错误";
		}
		
		@Override
		public String getCode() {
			return "400";
		}
		
		@Override
		public String toString() {
			return "PARAM_ERROR " + getCode() + " " + getMsg();
		}
		
	};
	
	/**
	 * 未知失败响应
	 * 
	 */
	ResponseHead FAILURE = new ResponseHead() {
		
		private static final long serialVersionUID = 1L;

		@Override
		public String getMsg() {
			return "系统异常";
		}
		
		@Override
		public String getCode() {
			return "500";
		}
		
		@Override
		public String toString() {
			return "FAILURE " + getCode() + " " + getMsg();
		}
	};
}
