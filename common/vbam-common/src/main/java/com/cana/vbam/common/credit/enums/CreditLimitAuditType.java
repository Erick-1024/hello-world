package com.cana.vbam.common.credit.enums;

/**
 * 授信额度变化记录类型
 * 
 * @author XuMeng
 *
 */
public enum CreditLimitAuditType {

	/**
	 * 当新生成一条额度或者一条额度的总额度发生变化，则生成一条此类型的额度变化记录
	 */
	TOTAL("总额度变化"),

	/**
	 * 业务项目中，额度消耗
	 */
	PAYMENT("额度消耗"),

	/**
	 * 业务项目中，自动还款额度恢复
	 */
	REFUND("额度自动恢复"),

	/**
	 * 当客户在融资模块进行还款时，触发的额度恢复记录
	 */
	REPAYMENT("融资还款恢复");

	private String desc;

	private CreditLimitAuditType(String desc) {
		this.desc = desc;
	}

	public String desc() {
		return desc;
	}

}
