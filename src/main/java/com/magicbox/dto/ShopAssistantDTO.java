package com.magicbox.dto;

import com.magicbox.model.Member;
import com.magicbox.model.ShopAssistant;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ShopAssistantDTO extends ShopAssistant {
	
	private static final long serialVersionUID = 1L;

	private Member member;
}
