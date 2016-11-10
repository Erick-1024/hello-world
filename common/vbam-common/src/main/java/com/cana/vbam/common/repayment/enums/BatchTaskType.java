package com.cana.vbam.common.repayment.enums;

public enum BatchTaskType {
	overdue_deduct("逾期账扣"),
	expense_deduct("固定费用账扣"),
	normal_deduct("正常账扣"),
	deduct("账扣"),
	extension_charge_generate("展期费用生成"),
	overdue_generate("逾期生成"),
	penalty_generate("罚息生成");
	
	private String desc;
	
	private BatchTaskType(String desc){
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}

	/**
	 * 是否是账扣类型的任务
	 * @return
	 */
	public boolean isDeductType() {
		return this == overdue_deduct || this == expense_deduct || this == normal_deduct || this == deduct;
	}

}
