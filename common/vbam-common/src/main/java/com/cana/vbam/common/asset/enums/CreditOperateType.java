package com.cana.vbam.common.asset.enums;

/**
 * @author hu
 *
 */
public enum CreditOperateType {

	CREATE("新增"),
	USE("占用"),
	FREEZE("冻结"),
	UNFREEZE("解冻"),
	MODIFY("变更"),
	RECOVER("释放"),
	REVOKE("撤销"),
	CANCEL("作废"),
	EXPIRE("到期");

	private String desc;

	CreditOperateType(String desc) {
		this.desc = desc;
	}

	public String desc() {
		return desc;
	}
}
