package com.cana.vbam.common.crawler.enums;

/**
 * 法院执行主体
 * @author renshui
 *
 */
public enum CourtExecutionSubject {
	individual("自然人"),
	company("法人或其他组织");
	
	private String desc;
	
	private CourtExecutionSubject(String desc){
		this.desc = desc;
	} 
	
	
	public String desc() {
		return desc;
	}
}
