package com.magicbox.base.utilities;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanMap;

/**
 * bean操作工具
 * 
 * @author xiangshuo
 *
 */
public final class XBeanUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(XBeanUtils.class);

	private XBeanUtils() {}
	
	static {
		ConvertUtils.register(new DateConverter(null), Date.class);
	}
	
	/**
	 * 复制源列表转换为clazz类型列表
	 * 
	 * @param srcList
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> copyList(List<?> srcList, Class<T> clazz) {
		if (CollectionUtils.isEmpty(srcList)) {
			return Collections.emptyList();
		}
		
		List<T> resultList = new ArrayList<>();
		
		for (Object obj : srcList) {
			
			if (null != obj) {
				
				try {
					T targetObj = clazz.newInstance();
					BeanUtils.copyProperties(obj, targetObj);
					
					resultList.add(targetObj);
				} catch (Exception e) {
					
					logger.error("bean转换异常", e);
					throw new RuntimeException("bean转换异常", e);
				}
			}
		}
		
		return resultList;
	}
	
	/**
	 * list转map
	 * 
	 * @param srcList
	 * @param keyClass
	 * @param keyName
	 * @return
	 */
	public static <K, V> Map<K, V> listToMap(List<V> srcList, Class<K> keyClass, String keyName) {
		if (CollectionUtils.isEmpty(srcList)) {
			return Collections.emptyMap();
		}
		
		PropertyDescriptor propDesc = null;
		
		Map<K, V> map = new HashMap<>();
		for (V each : srcList) {
			
			if (null != each) {
				
				if (null == propDesc) {
					
					propDesc = BeanUtils.getPropertyDescriptor(each.getClass(), keyName);
				}
				
				try {
					
					@SuppressWarnings("unchecked")
					K key = (K) propDesc.getReadMethod().invoke(each);
					if (null != key) {
						
						map.put(key, each);
					}
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					throw new RuntimeException("列表转map失败", e);
				}
			}
		}
		
		return map;
	}
	
	/**
	 * list转map，value多值
	 * 
	 * @param srcList
	 * @param keyClass
	 * @param keyName
	 * @return
	 */
	public static <K, V> Map<K, List<V>> listToValueListMap(List<V> srcList, Class<K> keyClass, String keyName) {
		if (CollectionUtils.isEmpty(srcList)) {
			return Collections.emptyMap();
		}
		
		PropertyDescriptor propDesc = null;
		
		Map<K, List<V>> map = new HashMap<>();
		for (V each : srcList) {
			
			if (null != each) {
				
				if (null == propDesc) {
					
					propDesc = BeanUtils.getPropertyDescriptor(each.getClass(), keyName);
				}
				
				try {
					
					@SuppressWarnings("unchecked")
					K key = (K) propDesc.getReadMethod().invoke(each);
					
					if (null != key) {
						
						List<V> valueList = map.get(key);
						
						if (null == valueList) {
							
							valueList = new ArrayList<>();
							map.put(key, valueList);
						}
						
						valueList.add(each);
					}
					
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					throw new RuntimeException("列表转map失败", e);
				}
			}
		}
		
		return map;
	}
	
	/**
	 * 提取bean中的某个字段
	 * 
	 * @param list
	 * @param fieldClass
	 * @param fieldName
	 * @return
	 */
	public static <T> List<T> extractField(List<?> list, Class<T> fieldClass, String fieldName) {
		if (CollectionUtils.isEmpty(list)) {
			return Collections.emptyList();
		}
		
		PropertyDescriptor propDesc = null;
		
		List<T> result = new ArrayList<>();
		for (Object each : list) {
			
			if (null != each) {
				
				if (null == propDesc) {
					
					propDesc = BeanUtils.getPropertyDescriptor(each.getClass(), fieldName);
				}
				
				try {
					
					@SuppressWarnings("unchecked")
					T fieldValue = (T) propDesc.getReadMethod().invoke(each);
					if (null != fieldValue) {
						result.add(fieldValue);
					}
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					throw new RuntimeException("列表提取字段失败", e);
				}
			}
		}
		
		return result;
	}
	
	/**
	 * bean转map，支持嵌套bean
	 * 
	 * @param bean
	 * @return
	 */
	public static Map<String, Object> beanToMap(Object bean) {
		if (null == bean) {
			return null;
		}
		
		Map<String, Object> map = simpleBeanToMap(bean);
		for (Entry<String, Object> entry : map.entrySet()) {
			
			if (null == entry.getValue()) {
				continue;
			}
			
			if (entry.getValue() instanceof Collection) {
				
				map.put(entry.getKey(), beansToMaps((Collection<?>) entry.getValue()));
			} else if (entry.getValue() instanceof Map) {
				
				// do nothing
			} else if (! isSimpleType(entry.getValue())) {
				
				map.put(entry.getKey(), beanToMap(entry.getValue()));
			}
		}
		
		return map;
	}
	
	private static Map<String, Object> simpleBeanToMap(Object bean) {
		if (bean instanceof Collection) {
			throw new IllegalArgumentException("bean是集合类，而不是简单对象");
		}
		
		BeanMap beanMap = BeanMap.create(bean);
		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) new HashMap<>(beanMap);
		return map;
	}
	
	private static boolean isSimpleType(Object value) {
		if (value.getClass().isPrimitive()) {
			return true;
		}
		if (value instanceof Boolean 
				|| value instanceof Character
				|| value instanceof Byte
				|| value instanceof Short
				|| value instanceof Integer
				|| value instanceof Long
				|| value instanceof Float
				|| value instanceof Double
				|| value instanceof String
				|| value instanceof Date
				|| value instanceof BigDecimal
				) {
			return true;
		}
		
		return false;
	}

	/**
	 * bean集合转map集合
	 * 
	 * @param collection
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> beansToMaps(Collection<?> collection) {
		if (null == collection) {
			return null;
		}
		
		List<Map<String, Object>> mapList = new ArrayList<>();
		
		for (Object object : collection) {
			
			if (object instanceof Map) {
				
				mapList.add((Map<String, Object>) object);
			} else {
				
				mapList.add(beanToMap(object));
			}
		}
		
		return mapList;
	}
	
	/**
	 * 转换map到bean
	 * 
	 * @param map
	 * @param clazz
	 * @return
	 */
	public static <T> T mapToBean(Map<String, ?> map, Class<T> clazz) {
		if (null == map || null == clazz) {
			return null;
		}
		
		try {
			
			T bean = clazz.newInstance();
			org.apache.commons.beanutils.BeanUtils.populate(bean, map);
			
			return bean;
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException("转换map到bean异常", e);
		}
	}
}
