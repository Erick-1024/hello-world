package com.cana.vbam.common.vj.enums;

public enum CustomerDataChangeType {
	created("新增放款信息"),
	only_mobileno_updated("仅手机号发生了更新"),
	all_updated("全部更新");
	
	
	private String desc;
	
	private CustomerDataChangeType(String desc){
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}

}
