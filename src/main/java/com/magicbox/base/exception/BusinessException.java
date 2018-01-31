package com.magicbox.base.exception;

import com.magicbox.base.support.ResponseHead;

/**
 * 业务异常
 * 
 * @author xiangshuo
 *
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private ResponseHead head;

	public BusinessException() {
		super();
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}

	public BusinessException(ResponseHead head, Throwable cause) {
		super(cause);
		this.head = head;
	}
	
	public BusinessException(ResponseHead head) {
		super(head.getMsg());
		this.head = head;
	}

	public ResponseHead getHead() {
		return head;
	}

	public void setHead(ResponseHead head) {
		this.head = head;
	}
	
	public static BusinessException getInstance(ResponseHead head) {
		return new BusinessException(head);
	}
}
