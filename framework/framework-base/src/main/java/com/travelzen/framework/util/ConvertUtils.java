package com.travelzen.framework.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.travelzen.framework.core.util.TZUtil;

public class ConvertUtils {

	public static <S, V> List<S> voes2models(Collection<V> coll, ModelConverter<S, V> converter) {
		if (TZUtil.isEmpty(coll)) {
			return null;
		}
		List<S> result = new ArrayList<>();
		for (V v : coll) {
			result.add(converter.vo2sm(v));
		}
		return result;
	}

	public static <S, V> List<V> models2voes(Collection<S> coll, ModelConverter<S, V> converter) {
		if (TZUtil.isEmpty(coll)) {
			return null;
		}
		List<V> result = new ArrayList<>();
		for (S s : coll) {
			result.add(converter.sm2vo(s));
		}
		return result;
	}

	public static <M extends V, V> List<V> convertChildren(List<M> list) {
		return list == null ? null : new ArrayList<V>(list);
	}

}
