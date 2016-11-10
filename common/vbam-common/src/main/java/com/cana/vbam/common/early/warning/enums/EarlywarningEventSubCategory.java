package com.cana.vbam.common.early.warning.enums;

public enum EarlywarningEventSubCategory {
	
	COUNTER_GUARANTEE_RATE(EarlywarningEventCategory.SYSTEM, "质押反担保覆盖率"),
	SALES_CHANGE_RATE(EarlywarningEventCategory.SYSTEM, "销售变化率"),
	SALES_REPAYMENT_RATE(EarlywarningEventCategory.SYSTEM, "销售回款率"),
	CONTINUE_OVERDUE_NUM(EarlywarningEventCategory.SYSTEM, "连续逾期次数"),
	TOTAL_OVERDUE_NUM(EarlywarningEventCategory.SYSTEM, "累计逾期次数");
	
	private EarlywarningEventCategory parentCategory;
	
	private String desc;
	
	private EarlywarningEventSubCategory(EarlywarningEventCategory parentCategory,String desc){
		this.parentCategory = parentCategory;
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
	
	public EarlywarningEventCategory parentCategory(){
		return parentCategory;
	}
	
}
