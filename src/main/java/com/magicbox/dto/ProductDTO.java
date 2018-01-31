package com.magicbox.dto;

import java.util.List;

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
}
