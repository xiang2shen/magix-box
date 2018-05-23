package com.magicbox.dto;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.magicbox.model.Box;
import com.magicbox.model.Product;
import com.magicbox.model.ProductImage;
import com.magicbox.model.ProductTag;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductDTO extends Product {
	
	private static final long serialVersionUID = 1L;

	private List<ProductImage> imageList;
	private List<ProductTag> tagList;
	private List<Box> boxList;
	
	public Integer getTotalStock() {
		
		int stock = 0;
		if (CollectionUtils.isNotEmpty(boxList)) {
			for (Box box : boxList) {
				if (null != box && null != box.getProductStock()) {
					stock += box.getProductStock();
				}
			}
		}
		
		return stock;
	}
}
