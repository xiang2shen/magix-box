package com.magicbox.base.constants;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 会员状态枚举
 * 
 * @author xiangshuo
 *
 */
public enum MemberStatusEnum {
	OK(1, "正常"),
	FROZEN(2, "冻结");

	
	private static final Map<Integer, String> VALUES_MAP;
	
	static {
		Map<Integer, String> map = new LinkedHashMap<>();
		for (MemberStatusEnum each : values()) {
			map.put(each.getCode(), each.getName());
		}
		VALUES_MAP = Collections.unmodifiableMap(map);
	}
	
	private Integer code;
	private String name;
	
	private MemberStatusEnum(Integer code, String name) {
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
	
	public static MemberStatusEnum getEnumByCode(Integer code) {
		for (MemberStatusEnum each : values()) {
			if (each.getCode().equals(code)) {
				return each;
			}
		}
		return null;
	}
	
	public static String getNameByCode(Integer code) {
		MemberStatusEnum en = getEnumByCode(code);
		if (null != en) {
			return en.getName();
		}
		return null;
	}
}
