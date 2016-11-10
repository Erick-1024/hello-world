package com.travelzen.framework.core.util;

public class NumberUtil {
	/**
	 * 比较两个Long的数值是否相等，如果均为null也认为是相等的
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean equals(Long a, Long b) {

		if (a == null && b == null)
			return true;
		if (a != null)
			return a.equals(b);
		else
			return false;
	}
	/**
	 * 比较两个Double的数值是否相等，如果均为null也认为是相等的
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean equals(Double a, Double b) {

		if (a == null && b == null)
			return true;
		if (a != null && b ==null)
			return false;
		if (a == null && b != null)
			return false;
		return a.compareTo(b) == 0;
	}
}
