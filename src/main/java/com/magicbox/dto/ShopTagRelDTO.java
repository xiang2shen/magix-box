package com.magicbox.dto;

import com.magicbox.model.ShopTagRel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ShopTagRelDTO extends ShopTagRel {
	
	private static final long serialVersionUID = 1L;

	private String tagName;
}
