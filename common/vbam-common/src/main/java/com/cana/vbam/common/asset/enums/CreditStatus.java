package com.cana.vbam.common.asset.enums;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * @author hu
 *
 */
public enum CreditStatus {

	APPLY("申请"),
	NORMAL("生效"),
	FREEZE("冻结"),
	REVOKE("撤销"),
	CANCEL("作废"),
	EXPIRE("到期");
	
	private String desc;

	CreditStatus(String desc) {
		this.desc = desc;
	}

	public String desc() {
		return desc;
	}

	public static List<String> canAlterState(){
		return Lists.newArrayList(APPLY.name(), NORMAL.name());
	}
}
