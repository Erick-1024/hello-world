package com.travelzen.framework.retry.policy;


public enum RetryTaskBackOffPolicy {
	fixed("固定");
	
	private String desc;

	private RetryTaskBackOffPolicy(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

}
