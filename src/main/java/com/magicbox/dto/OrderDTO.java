package com.magicbox.dto;

import com.magicbox.model.Order;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderDTO extends Order {
	
	private static final long serialVersionUID = 1L;

	private String shopName;
}
