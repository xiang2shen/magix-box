package com.magicbox.base.utilities;

import java.util.List;

import org.springframework.util.CollectionUtils;

/**
 * 集合操作工具
 * 
 * @author xiangshuo
 *
 */
public final class XCollectionUtils {

	private XCollectionUtils() {}
	
	/**
	 * 获取列表第一个元素
	 * 
	 * @param list
	 * @return 若列表为空，则返回null
	 */
	public static <T> T getFirstElement(List<T> list) {
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		
		return list.get(0);
	}
}
