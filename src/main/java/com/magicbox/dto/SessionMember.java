package com.magicbox.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * session中存放的会员信息
 * 
 * @author xiangshuo
 *
 */
@Data
public class SessionMember implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public SessionMember() {}
	
	public SessionMember(Long memberId) {
		this.memberId = memberId;
	}

	private Long memberId;
}
