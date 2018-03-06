package com.magicbox.base.constants;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public enum FrameStatusEnum {
	UNBIND(1, "未绑定店铺"),
	BOUND(10, "已绑定店铺");

	
	private static final Map<Integer, String> VALUES_MAP;
	
	static {
		Map<Integer, String> map = new LinkedHashMap<>();
		for (FrameStatusEnum each : values()) {
			map.put(each.getCode(), each.getName());
		}
		VALUES_MAP = Collections.unmodifiableMap(map);
	}
	
	private Integer code;
	private String name;
	
	private FrameStatusEnum(Integer code, String name) {
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
	
	public static FrameStatusEnum getEnumByCode(Integer code) {
		for (FrameStatusEnum each : values()) {
			if (each.getCode().equals(code)) {
				return each;
			}
		}
		return null;
	}
	
	public static String getNameByCode(Integer code) {
		FrameStatusEnum en = getEnumByCode(code);
		if (null != en) {
			return en.getName();
		}
		return null;
	}
}
