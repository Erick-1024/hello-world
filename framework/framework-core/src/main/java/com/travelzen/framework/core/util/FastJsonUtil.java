package com.travelzen.framework.core.util;

import com.alibaba.fastjson.JSON;

public class FastJsonUtil {

	public static String getJsonString(Object object){
		return JSON.toJSONString(object);
	}

	public static <T> T parseJsonString(String jsonString,Class<T> clazz){
		return JSON.parseObject(jsonString, clazz);
	}

	public static JSON toJsonObject(String jsonString){
		return JSON.parseObject(jsonString);
	}

}
