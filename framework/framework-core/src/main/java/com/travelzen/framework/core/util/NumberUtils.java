package com.travelzen.framework.core.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @since 2014-1-17 下午2:38:59
 * @author cheguangai
 * @TODO TODO
 */
public class NumberUtils {
	
	public static Double getValue(Double d){
		return d == null ? Double.valueOf(0.0) : d;
	}
	
	public static Integer getValue(Integer i){
		return i == null ? Integer.valueOf(0) : i;
	}
	
	public static Float getValue(Float f){
		return f == null ? Float.valueOf(0.0f) : f;
	}
	
	public static Double getDoubleValue(String d){
		if(StringUtils.isBlank(d))return 0d;
		return Double.valueOf(d);
	}
	
	public static Long getValue(Long l){
		return l == null ? Long.valueOf(0) : l;
	}
	
	
}
