package com.cana.vbam.common.asset.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hu
 * 专项计划状态
 */
public enum SpecialProgramStatus {

	CREATE("立项"),
	PACKAGE("封包"),
	ISSUE("发行"),
	ESTABLISH("成立"),
	CLOSE("结束");
	
	private String desc;
	
	private SpecialProgramStatus(String desc){
		this.desc = desc;
	}

	public String desc() {
		return desc;
	}
	
	/**
	 * 发行后专项计划枚举状态
	 * @param name
	 * @return
	 */
	public List<SpecialProgramStatus> getAll(String name) {
		SpecialProgramStatus[] aa = SpecialProgramStatus.values();
		List<SpecialProgramStatus> bb = new ArrayList<SpecialProgramStatus>();
		for(SpecialProgramStatus a : aa) {
			if(!a.name().equals(name)) {
				bb.add(a);
			}
		}
		return bb;
	}

}
