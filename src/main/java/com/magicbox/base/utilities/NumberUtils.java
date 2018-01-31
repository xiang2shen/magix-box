package com.magicbox.base.utilities;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 数字工具类
 * 
 * @author xiangshuo
 *
 */
public final class NumberUtils {

	private NumberUtils() {}
	
	/**
	 * 整数（单位：分）转金额（单位：元）
	 * 
	 * @param num
	 * @return
	 */
	public static BigDecimal toMoney(Integer num) {
		return BigDecimal.valueOf(num).divide(BigDecimal.valueOf(100), 2, RoundingMode.DOWN);
	}
}
