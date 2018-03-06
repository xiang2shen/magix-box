package com.magicbox.dto;

import com.magicbox.model.Shop;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ShopDistanceDTO extends Shop {
	
	private static final long serialVersionUID = 1L;

	private Integer distance;
}
