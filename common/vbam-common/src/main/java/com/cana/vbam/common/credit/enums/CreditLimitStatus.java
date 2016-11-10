package com.cana.vbam.common.credit.enums;

/**
 * 额度状态，仅在额度状态为正常时，才允许客户使用额度。
 * 
 * 不管额度状态是什么，都应该允许额度恢复操作。
 * 
 * @author XuMeng
 *
 */
public enum CreditLimitStatus {

	NORMAL("正常"), FREEZE("冻结"), TEMPORARY_FREEZE("临时冻结"), DISABLED("停用"), INACTIVE("未激活");

	private String desc;

	private CreditLimitStatus(String desc) {
		this.desc = desc;
	}

	public String desc() {
		return desc;
	}

}
