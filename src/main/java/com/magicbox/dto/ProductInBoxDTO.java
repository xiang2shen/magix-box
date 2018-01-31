package com.magicbox.dto;

import java.io.Serializable;

import com.magicbox.model.Box;
import com.magicbox.model.Product;

import lombok.Data;

@Data
public class ProductInBoxDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Product product;
	private Box box;
}
