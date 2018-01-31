package com.magicbox.dto;

import java.util.List;

import com.magicbox.model.Shop;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ShopDTO extends Shop {
	
	private static final long serialVersionUID = 1L;

	private List<ProductDTO> productList;
}
