package com.cana.vbam.common.early.warning.enums;

/**
 * 韵达预警种类
 * @author sugar
 *
 */
public enum YundaexEarlywarningEventCategory {
	
	DEPARTMENTS_PUNISH("是否收到税务、工商或质检等部门处罚"),
	BUSINESS_NATURE_CHANGE("公司业务性质有无重大变化"),
	OWNERSHIP_STRUCTURE_CHANGE("股权结构变化"),
	NEGATIVE_NEWS("负面新闻"),
	LITIGATION_DISPUTE("诉讼纠纷"),
	SHORT_TERM_LOAN("短期借款"),
	CUSTOMER_ATTITUDE("客户态度"),
	OTHER("其它"),
	SYSTEM("系统");
	
	private String desc;
	
	private YundaexEarlywarningEventCategory(String desc){
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
	
}
