package com.travelzen.mongo.vo;

public class AccesserInfoHolder {

	private static ThreadLocal<AccesserInfo> holder = new ThreadLocal<>();

	public static AccesserInfo get() {
		return holder.get();
	}

	public static void set(AccesserInfo userKey) {
		holder.set(userKey);
	}

	public static void remove() {
		holder.remove();
	}
}
