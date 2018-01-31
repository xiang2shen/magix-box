package com.magicbox.base.utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;

import com.alibaba.fastjson.JSON;

/**
 * json工具
 * 
 * @author xiangshuo
 *
 */
public final class JsonUtils {
	
    private JsonUtils() {}
    
    /**
     * 格式化成json
     * 
     * @param obj
     * @return
     */
    public static String format(Object obj) {
    	
    	return JSON.toJSONString(obj);
    }
    
    /**
     * 解析单个对象
     * 
     * @param json
     * @param clazz
     * @return
     */
    public static <T> T parseObject(String json, Class<T> clazz) {
    	
    	return JSON.parseObject(json, clazz);
    }
    
    /**
     * 解析数组
     * 
     * @param json
     * @param clazz
     * @return
     */
    public static <T> List<T> parseArray(String json, Class<T> clazz) {
    	
    	return JSON.parseArray(json, clazz);
    }
    
    /**
     * 解析map数组
     * 
     * @param json
     * @return
     */
    @SuppressWarnings("rawtypes")
	public static List<Map<String, Object>> parseMapArray(String json) {
    	
    	List<Map> mapList = JSON.parseArray(json, Map.class);
    	
    	List<Map<String, Object>> newMapList = new ArrayList<>();
    	
    	if (CollectionUtils.isNotEmpty(mapList)) {
			
    		for (Map map : mapList) {
			
    			if (null != map) {
					
    				@SuppressWarnings("unchecked")
    				Map<String, Object> newMap = map;
    				newMapList.add(newMap);
				}
			}
		}
    	
    	return newMapList;
    }
}
