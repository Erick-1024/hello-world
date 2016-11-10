package com.travelzen.framework.core.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;

public class CollectionUtil {
	
	/**
	 * 取两个集合的交集
	 * @param a
	 * @param b
	 */
	public static <E> void intersection(final Collection<E> a, final Collection<E> b) {
		if (b != null && !b.isEmpty()) {
			if (a.isEmpty()) {
				a.addAll(b);
			} else {
				a.retainAll(b);
			}
		}
	}
	
	/**
	 * 去除集合中字符串中的空格
	 * @param stringCollection
	 */
	public static void string2UpperCase(final Collection<String> stringCollection){
		CollectionUtils.transform(stringCollection, new Transformer() {
			@Override
			public Object transform(Object input) {
				if (input != null)
					return ((String) input).toUpperCase();
				else
					return input;
			}

		});
	}
	
	/**
	 * 去除集合中重复的记录
	 * @param stringCollection
	 */
	public static <E> List<E> removeDuplicate(final List<E> list){
		if(list == null)
			return null;
		return new ArrayList<E>(new LinkedHashSet<E>(list));
	}
	
	/**
	 * 比较两个集合是否完全相等, 两个集合若都等于null，认为是相等
	 * @param a
	 * @param b
	 * @return
	 */
	public static <E> boolean isEqualCollection(final Collection<E> a, final Collection<E> b){
		if(a == null && b == null)
			return true;
		if(a == null && b != null)
			return false;
		if(a != null && b == null)
			return false;
		return CollectionUtils.isEqualCollection(a, b);
	}
}
