package com.cana.vbam.common.credit.enums;

/**
 * 真旅客户申请人类型
 * @author XuMeng
 *
 */
public enum ApplyApplicantType {

	company("企业"),
	individual("个人");
	
	private String desc;
	
	private ApplyApplicantType(String desc) {
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}

	public static String getDesc(String value) {
		if (company.name().equals(value)) {
			return company.desc();
		} else if (individual.name().equals(value)) {
			return individual.desc();
		} else {
			return "不确定";
		}
	}
}
