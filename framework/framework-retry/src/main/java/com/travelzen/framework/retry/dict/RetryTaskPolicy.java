package com.travelzen.framework.retry.dict;


public enum RetryTaskPolicy {
	simple("最大执行次数");
	
	private String desc;

	private RetryTaskPolicy(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

}
