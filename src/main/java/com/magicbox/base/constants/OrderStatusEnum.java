package com.magicbox.base.constants;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 订单状态枚举
 * 
 * @author xiangshuo
 *
 */
public enum OrderStatusEnum {
	UNPAY(1, "未支付"),
	PAY(10, "已支付"),
	UNDONE(50, "未取货完成"),
	DONE(100, "取货完成");

	
	private static final Map<Integer, String> VALUES_MAP;
	
	static {
		Map<Integer, String> map = new LinkedHashMap<>();
		for (OrderStatusEnum each : values()) {
			map.put(each.getCode(), each.getName());
		}
		VALUES_MAP = Collections.unmodifiableMap(map);
	}
	
	private Integer code;
	private String name;
	
	private OrderStatusEnum(Integer code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public Integer getCode() {
		return code;
	}
	public String getName() {
		return name;
	}
	
	public static Map<Integer, String> toMap() {
		return VALUES_MAP;
	}
	
	public static OrderStatusEnum getEnumByCode(Integer code) {
		for (OrderStatusEnum each : values()) {
			if (each.getCode().equals(code)) {
				return each;
			}
		}
		return null;
	}
	
	public static String getNameByCode(Integer code) {
		OrderStatusEnum en = getEnumByCode(code);
		if (null != en) {
			return en.getName();
		}
		return null;
	}
}
