package com.cana.vbam.common.asset.enums;

import java.util.Arrays;
import java.util.List;


/**
 * 项目中资金方的合作状态
 * @author XuMeng
 *
 */
public enum ProjectFactorStatusEnum {
	
	NORMAL("正常"),
	PAUSE("暂停");

	private String desc;

	ProjectFactorStatusEnum(String desc) {
		this.desc = desc;
	}

	public String desc() {
		return desc;
	}
	
	public static List<ProjectFactorStatusEnum> projectFactorStatusEnums(){
		return Arrays.asList(NORMAL, PAUSE);
	}

}
