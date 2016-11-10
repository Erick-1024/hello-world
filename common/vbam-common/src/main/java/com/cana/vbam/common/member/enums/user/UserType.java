package com.cana.vbam.common.member.enums.user;

import java.util.Arrays;
import java.util.List;

public enum UserType {
	
	FACTOR("保理商"),
	FINACE("融资客户"),
	CORECOMPANY("核心企业"),
	CANA("系统管理员"),
	BROKERTRUSTORG("券商/信托"),
	OTHERORG("其他机构"),
	INDIVIDUAL("个人融资客户");
	
	private String desc;
	
	private UserType(String desc){
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
	
	/**
	 * 非个人客户类型
	 * @return
	 */
	public static List<UserType> nonIndividualUserTypes(){
		return Arrays.asList(FACTOR, FINACE, CORECOMPANY, CANA, BROKERTRUSTORG, OTHERORG);
	}

}
