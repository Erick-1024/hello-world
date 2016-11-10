package com.cana.vbam.common.customer.enums;

import java.util.List;

import com.google.common.collect.Lists;

public enum CustomerMaterialSubmitState {

	UNSUBMIT("未提交"),
	PARTSUBMIT("提交部分"),
	SUBMITTED("已提交"),
	;
	
	private String desc;
	
	private CustomerMaterialSubmitState(String desc) {
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
	
	public static List<CustomerMaterialSubmitState> getSearchCondition(){
		return Lists.newArrayList(PARTSUBMIT, SUBMITTED);
	}
}
