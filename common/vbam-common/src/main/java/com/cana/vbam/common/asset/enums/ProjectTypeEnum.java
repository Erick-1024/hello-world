package com.cana.vbam.common.asset.enums;

/**
 * @author Ren.jiangzhou
 * @time 2016年5月17日下午5:38:27
 */
public enum ProjectTypeEnum {
	
	// factor("保理"), rental("租赁"), small_loan("小贷");

	platform("平台");

	private String desc;

	ProjectTypeEnum(String desc) {
		this.desc = desc;
	}

	public String desc() {
		return desc;
	}

}
