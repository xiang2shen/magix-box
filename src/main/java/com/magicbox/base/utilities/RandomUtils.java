package com.magicbox.base.utilities;

import java.util.Random;

/**
 * 随机工具
 * 
 * @author xiangshuo
 *
 */
public final class RandomUtils {

	private RandomUtils() {}
	
	private static final String NUMBER_STRING = "1234567890";
	private static final String LOWCASE_STRING = "abcdefghijklmnopqrstuvwxyz";
	private static final String UPCASE_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String ALL_STRING = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	/**
	 * 随机数字字符串
	 * 
	 * @param length
	 * @return
	 */
	public static String randomNumberString(int length) {
		return randomString(length, NUMBER_STRING);
	}
	
	/**
	 * 随机字符串
	 * 
	 * @param length
	 * @return
	 */
	public static String randomString(int length) {
		return randomString(length, ALL_STRING);
	}
	
	/**
	 * 随机小写字符串
	 * 
	 * @param length
	 * @return
	 */
	public static String randomLowCaseString(int length) {
		return randomString(length, LOWCASE_STRING);
	}
	
	/**
	 * 随机大写字符串
	 * 
	 * @param length
	 * @return
	 */
	public static String randomUpCaseString(int length) {
		return randomString(length, UPCASE_STRING);
	}
	
	/**
	 * 随机中文字符串
	 * 
	 * @param length
	 * @return
	 */
	public static String randomZnString(int length) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb.append((char) (new Random().nextInt(0x9FA5 - 0x4E00) + 0x4E00));
		}
		return sb.toString();
	}
	
	/**
	 * 随机字符串
	 * 
	 * @param length
	 * @param source 源字符串，从中获取字符
	 * @return
	 */
	public static String randomString(int length, String source) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb.append(source.charAt(new Random().nextInt(source.length())));
		}
		return sb.toString();
	}
}
