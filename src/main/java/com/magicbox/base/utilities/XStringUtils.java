package com.magicbox.base.utilities;

/**
 * 字符串操作工具
 * 
 * @author xiangshuo
 *
 */
public final class XStringUtils {

	private XStringUtils() {}
	
	/**
	 * 向前追加‘0’，直到到达固定长度
	 * 
	 * @param text
	 * @param length
	 * @return
	 */
	public static String prependWithZero(String text, int length) {
		StringBuilder sb = new StringBuilder();
		
		if (null != text) {
			sb.append(text);
		}
		
		for (int i = 0; i < length - sb.length(); i++) {
			sb.insert(0, '0');
		}
		
		return sb.toString();
	}
}
