package com.magicbox.base.constants;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 店员状态枚举
 * 
 * @author xiangshuo
 * 
 */
public enum ShopAssistantStatusEnum {
	UNCHECK(1, "待审核"),
	CHECK_FAILURE(5, "审核驳回"),
	CHECK_SUCCESS(10, "审核通过");

	
	private static final Map<Integer, String> VALUES_MAP;
	
	static {
		Map<Integer, String> map = new LinkedHashMap<>();
		for (ShopAssistantStatusEnum each : values()) {
			map.put(each.getCode(), each.getName());
		}
		VALUES_MAP = Collections.unmodifiableMap(map);
	}
	
	private Integer code;
	private String name;
	
	private ShopAssistantStatusEnum(Integer code, String name) {
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
	
	public static ShopAssistantStatusEnum getEnumByCode(Integer code) {
		for (ShopAssistantStatusEnum each : values()) {
			if (each.getCode().equals(code)) {
				return each;
			}
		}
		return null;
	}
	
	public static String getNameByCode(Integer code) {
		ShopAssistantStatusEnum en = getEnumByCode(code);
		if (null != en) {
			return en.getName();
		}
		return null;
	}
}
