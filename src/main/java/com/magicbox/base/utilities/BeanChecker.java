package com.magicbox.base.utilities;

import java.util.Collection;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.magicbox.base.exception.BusinessException;
import com.magicbox.base.support.Page;
import com.magicbox.base.support.ResponseHead;

/**
 * 参数校验工具类
 * 
 * @author xiangshuo
 *
 */
public class BeanChecker {

	public static final String REGEX_MOBILE = "^\\d{11}$";
	public static final String REGEX_ID_CARD_13 = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
	public static final String REGEX_ID_CARD_18 = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$";
	

	private BeanChecker() {
	}

	public static final BeanChecker getInstance() {
		return Holder.INSTANCE;
	}

	public BeanChecker notNull(Object param) {
		if (null == param) {
			throw new BusinessException(ResponseHead.PARAM_ERROR);
		}
		return this;
	}

	public BeanChecker notBlank(String param) {
		if (StringUtils.isBlank(param)) {
			throw new BusinessException(ResponseHead.PARAM_ERROR);
		}
		return this;
	}

	public BeanChecker notEmpty(Collection<?> collection) {
		if (null == collection || 0 == collection.size()) {
			throw new BusinessException(ResponseHead.PARAM_ERROR);
		}
		return this;
	}

	public BeanChecker notEmpty(Map<?, ?> map) {
		if (null == map || 0 == map.size()) {
			throw new BusinessException(ResponseHead.PARAM_ERROR);
		}
		return this;
	}

	public BeanChecker gt(Number num1, Number num2) {
		notNull(num1).notNull(num2);
		if (num1.doubleValue() <= num2.doubleValue()) {
			throw new BusinessException(ResponseHead.PARAM_ERROR);
		}
		return this;
	}

	public BeanChecker ge(Number num1, Number num2) {
		notNull(num1).notNull(num2);
		if (num1.doubleValue() < num2.doubleValue()) {
			throw new BusinessException(ResponseHead.PARAM_ERROR);
		}
		return this;
	}

	public BeanChecker equals(Object obj1, Object obj2) {
		notNull(obj1).notNull(obj2);
		if (!obj1.equals(obj2)) {
			throw new BusinessException(ResponseHead.PARAM_ERROR);
		}
		return this;
	}

	public BeanChecker lt(Number num1, Number num2) {
		notNull(num1).notNull(num2);
		if (num1.doubleValue() >= num2.doubleValue()) {
			throw new BusinessException(ResponseHead.PARAM_ERROR);
		}
		return this;
	}

	public BeanChecker le(Number num1, Number num2) {
		notNull(num1).notNull(num2);
		if (num1.doubleValue() > num2.doubleValue()) {
			throw new BusinessException(ResponseHead.PARAM_ERROR);
		}
		return this;
	}

	public BeanChecker isTrue(Boolean param) {
		notNull(param);
		if (!param) {
			throw new BusinessException(ResponseHead.PARAM_ERROR);
		}
		return this;
	}
	
	public BeanChecker positive(Number num) {
		notNull(num);
		if (num.doubleValue() <= 0) {
			throw new BusinessException(ResponseHead.PARAM_ERROR);
		}
		return this;
	}
	
	public BeanChecker positiveOrZero(Number num) {
		notNull(num);
		if (num.doubleValue() < 0) {
			throw new BusinessException(ResponseHead.PARAM_ERROR);
		}
		return this;
	}

	public BeanChecker mobile(String param) {
		notNull(param);
		if (!Pattern.matches(REGEX_MOBILE, param)) {
			throw new BusinessException(ResponseHead.PARAM_ERROR);
		}
		return this;
	}
	
	public BeanChecker enumMap(Integer code, Map<Integer, String> enumMap) {
		notNull(code);
		notNull(enumMap);
		if (null == enumMap.get(code)) {
			throw new BusinessException(ResponseHead.PARAM_ERROR);
		}
		return this;
	}
	
	public BeanChecker page(Integer pageNo, Integer pageSize) {
		gt(pageNo, 0);
		gt(pageSize, 0);
		le(pageSize, Page.MAX_PAGE_SIZE);
		
		return this;
	}

	private static class Holder {
		private static final BeanChecker INSTANCE = new BeanChecker();
	}
	
}
