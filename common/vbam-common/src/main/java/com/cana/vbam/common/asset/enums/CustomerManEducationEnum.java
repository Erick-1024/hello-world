package com.cana.vbam.common.asset.enums;


/**
 * 客户高管学历枚举
 * @author jiangzhou.Ren
 * @time 2016年8月1日上午8:45:55
 */
public enum CustomerManEducationEnum {
	
	E_080("博士"),
	E_070("硕士"),
	E_060("本科"),
	E_050("专科"),
	OTHER("其它");
	
	private String desc;

	CustomerManEducationEnum(String desc) {
		this.desc = desc;
	}

	public String desc() {
		return desc;
	}
	

}
