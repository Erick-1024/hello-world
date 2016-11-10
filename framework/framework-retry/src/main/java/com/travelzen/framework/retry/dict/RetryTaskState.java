package com.travelzen.framework.retry.dict;


public enum RetryTaskState {
	not_begin("未开始"), 
	processing("处理中"), 
	success("处理成功"), 
	fail("处理失败");
	
	private String desc;

	private RetryTaskState(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

}
