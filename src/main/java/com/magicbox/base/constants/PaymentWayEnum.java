package com.magicbox.base.constants;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 支付方式枚举
 * 
 * @author xiangshuo
 *
 */
public enum PaymentWayEnum {
	ALI_PAY(1, "支付宝"),
	WEIXIN_PAY(2, "微信支付");

	
	private static final Map<Integer, String> VALUES_MAP;
	
	static {
		Map<Integer, String> map = new LinkedHashMap<>();
		for (PaymentWayEnum each : values()) {
			map.put(each.getCode(), each.getName());
		}
		VALUES_MAP = Collections.unmodifiableMap(map);
	}
	
	private Integer code;
	private String name;
	
	private PaymentWayEnum(Integer code, String name) {
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
	
	public static PaymentWayEnum getEnumByCode(Integer code) {
		for (PaymentWayEnum each : values()) {
			if (each.getCode().equals(code)) {
				return each;
			}
		}
		return null;
	}
	
	public static String getNameByCode(Integer code) {
		PaymentWayEnum en = getEnumByCode(code);
		if (null != en) {
			return en.getName();
		}
		return null;
	}
}
