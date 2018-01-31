package com.magicbox.base.support;

import java.io.Serializable;
import java.util.Map;

/**
 * 服务响应包装器
 * 
 * @author xiangshuo
 *
 */
public class ResponseWrapper<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ResponseHead head;
	
	private T body;
	
	/**
	 * 构造成功响应
	 * 
	 */
	public ResponseWrapper() {
		this.head = ResponseHead.SUCCESS;
	}
	
	/**
	 * 构造成功响应
	 * 
	 * @param body
	 */
	public ResponseWrapper(T body) {
		this();
		this.body = body;
	}
	
	/**
	 * 构造响应
	 * 
	 * @param head 响应头部
	 */
	public ResponseWrapper(ResponseHead head) {
		this.head = head;
	}
	
	/**
	 * 构造响应
	 * 
	 * @param head
	 * @param body
	 */
	public ResponseWrapper(ResponseHead head, T body) {
		this.head = head;
		this.body = body;
	}
	
	/**
	 * 是否是成功的响应
	 * 
	 * @return
	 */
	public boolean isSuccessful() {
		if (null != getHead()) {
			return ResponseHead.SUCCESS.getCode().equals(getHead().getCode());
		}
		
		return false;
	}
	
	/**
	 * 是否是失败的响应
	 * 
	 * @return
	 */
	public boolean isFailed() {
		return ! isSuccessful();
	}
	
	/**
	 * 获取响应
	 * 
	 * @param head
	 * @param body
	 * @return
	 */
	public static <T> ResponseWrapper<T> response(ResponseHead head, T body) {
		return new ResponseWrapper<>(head, body);
	}
	
	/**
	 * 获取成功响应
	 * 
	 * @param body
	 * @return
	 */
	public static <T> ResponseWrapper<T> succeed(T body) {
		return response(ResponseHead.SUCCESS, body);
	}
	
	/**
	 * 获取成功响应
	 * 
	 * @return
	 */
	public static <T> ResponseWrapper<T> succeed() {
		return succeed(null);
	}
	
	/**
	 * 获取失败响应
	 * 
	 * @param head
	 * @param body
	 * @return
	 */
	public static <T> ResponseWrapper<T> fail(ResponseHead head, T body) {
		return response(head, body);
	}
	
	/**
	 * 获取失败响应
	 * 
	 * @param head
	 * @return
	 */
	public static <T> ResponseWrapper<T> fail(ResponseHead head) {
		return fail(head, null);
	}
	
	/**
	 * 获取未知失败响应
	 * 
	 * @param body
	 * @return
	 */
	public static <T> ResponseWrapper<T> fail(T body) {
		return fail(ResponseHead.FAILURE, body);
	}
	
	/**
	 * 获取未知失败响应
	 * 
	 * @return
	 */
	public static <T> ResponseWrapper<T> fail() {
		return fail(ResponseHead.FAILURE);
	}
	
	/**
	 * 将map转为response
	 * 
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static ResponseWrapper<Map<String, Object>> fromMap(Map<String, Object> map) {
		try {
			
			Map<String, Object> headMap = (Map<String, Object>) map.get("head");
			InnerHead head = new InnerHead();
			head.setCode((String) headMap.get("code"));
			head.setMsg((String) headMap.get("msg"));
			
			Map<String, Object> bodyMap = (Map<String, Object>) map.get("body");
			
			return ResponseWrapper.response(head, bodyMap);
		} catch (Exception e) {
			return null;
		}
	}

	public ResponseHead getHead() {
		return head;
	}

	public void setHead(ResponseHead head) {
		this.head = head;
	}

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "ResponseWrapper [head=" + (null == head ? null : (head.getCode() + ":" + head.getMsg())) + ", body=" + body + "]";
	}
	
	static class InnerHead implements ResponseHead, Serializable {

		private static final long serialVersionUID = 1L;
		
		private String code;
		private String msg;
		
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getMsg() {
			return msg;
		}
		public void setMsg(String msg) {
			this.msg = msg;
		}
		
		@Override
		public String toString() {
			return "InnerHead [code=" + code + ", msg=" + msg + "]";
		}
		
	}
}
