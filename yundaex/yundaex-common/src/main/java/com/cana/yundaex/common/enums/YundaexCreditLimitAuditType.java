package com.cana.yundaex.common.enums;

/**
 * 授信额度变化记录类型
 * @author guguanggong
 *
 */
public enum YundaexCreditLimitAuditType {
	CREATE("生成额度"),
	OCCUPY("融资记录占用额度"),
	ERROROCCUPY("融资失败恢复额度"),
	AOUTRESTORE("自动还款额度恢复"),
	ACTIVEREPAYMENT("主动还款额度恢复");
	
	private String desc;
	
	private YundaexCreditLimitAuditType(String desc) {
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
}
