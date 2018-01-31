package com.magicbox.base.utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.magicbox.base.support.Page;

/**
 * 分页工具类
 * 
 * @author xiangshuo
 *
 */
public final class PageUtils {

	private PageUtils() {}
	
	/**
	 * 是否是空的分页
	 * 
	 * @param page
	 * @return
	 */
	public static boolean isEmpty(Page<?> page) {
		return null == page || null == page.getItems() || 0 == page.getItems().size();
	}
	
	/**
	 * 是否不是空的分页
	 * 
	 * @param page
	 * @return
	 */
	public static boolean isNotEmpty(Page<?> page) {
		return ! isEmpty(page);
	}

	/**
	 * 空分页
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> Page<T> emptyPage(int pageNo, int pageSize) {
		return new Page<T>(pageNo, pageSize, (List<T>) Collections.emptyList(), 0L);
	}

	/**
	 * 复制分页对象转为新的类型
	 * 
	 * @param srcPage
	 * @param targetClass
	 * @return
	 */
	public static <T> Page<T> copy(Page<?> srcPage, Class<T> targetClass) {
		
		if (null == srcPage) {
			return null;
		}
		
		if (isEmpty(srcPage)) {
			return emptyPage(srcPage.getPageNo(), srcPage.getPageSize());
		}
		
		List<T> targetList = new ArrayList<>();
		for (Object obj : srcPage) {
			
			if (null != obj) {
				
				T nObj = BeanUtils.instantiate(targetClass);
				BeanUtils.copyProperties(obj, nObj);
				targetList.add(nObj);
			}
		}
		
		return new Page<>(srcPage.getPageNo(), srcPage.getPageSize(), targetList, srcPage.getCount());
	}
}
