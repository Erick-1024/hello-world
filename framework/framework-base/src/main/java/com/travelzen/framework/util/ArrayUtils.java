package com.travelzen.framework.util;

/**
 * 
 * @since 2014-4-18 下午8:07:47
 * @author cheguangai
 * 
 */
public class ArrayUtils {

	public static String toString(Object[] arr){
		return ArrayUtils.toString(arr, ',');
	}
	
	public static String toString(Object[] arr, char delimiter){
		if(arr == null || arr.length == 0){
			return "";
		}else{
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<arr.length; i++){
				Object obj = arr[i];
				if(obj == null){
					continue;
				}
				sb.append(obj.toString());
				if(i < arr.length - 1){
					sb.append(delimiter);
				}
			}
			return sb.toString();
		}
	}
	
}
