package com.travelzen.framework.enums;

public enum SortOrder {

	ASC("asc"),

	DESC("desc");

	private String value;

	private SortOrder(String val) {
		this.value = val;
	}

	public String value() {
		return this.value;
	}

	public static SortOrder getByValue(String value) {
		if ("asc".equals(value)) {
			return ASC;
		} else if ("desc".equals(value)) {
			return DESC;
		}
		return null;
	}

}
