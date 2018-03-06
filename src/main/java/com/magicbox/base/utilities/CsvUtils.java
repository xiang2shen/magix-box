package com.magicbox.base.utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * csv工具
 * 
 * @author xiangshuo
 *
 */
public final class CsvUtils {

	private CsvUtils() {}
	
	/**
	 * 解析csv
	 * 
	 * @param csv
	 * @return
	 */
	public static List<String> parse(String csv) {
		return parse(csv, ",");
	}
	
	public static List<String> parse(String csv, String separator) {
		
		if (StringUtils.isBlank(csv)) {
			return Collections.emptyList();
		}
		
		List<String> list = new ArrayList<>();
		
		if (! csv.contains(separator)) {
			
			list.add(csv);
		} else {
			
			String[] arr = StringUtils.split(csv, separator);
			for (String s : arr) {
				
				if (StringUtils.isNotBlank(s)) {
					list.add(s);
				}
			}
		}
		
		return list;
	}
	
	/**
	 * 格式化成csv
	 * 
	 * @param list
	 * @return
	 */
	public static String format(List<String> list) {
		return format(list, ",");
	}
	
	public static String format(List<String> list, String separator) {
		if (null == list) {
			return null;
		}
		
		if (list.size() == 0) {
			return "";
		}
		
		StringBuilder sb = new StringBuilder();
		for (String str : list) {
			
			sb.append(str);
			sb.append(separator);
		}
		
		return sb.substring(0, sb.length() - 1);
	}

	public static List<Long> parseLong(String csv) {
		List<String> strList = parse(csv);
		
		List<Long> result = new ArrayList<>();
		
		for (String s : strList) {
			Long l = Long.parseLong(s);
			result.add(l);
		}
		
		return result;
	}
}
