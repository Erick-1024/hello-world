package com.cana.vbam.common.asset.enums;

import org.apache.commons.lang3.StringUtils;

public enum SupervisionAgencyEnum {

	DEALERS_ASSOCIATION_ABN("交易商协会ABN"),
	CBRC_MANAGER_ABS("银监会主管ABS"),
	SFC_MANAGER_ABS("证监会主管ABS");
	
	private String desc;
	
	private SupervisionAgencyEnum(String desc){
		this.desc = desc;
	}

	public String desc() {
		return desc;
	}
	
	public static SupervisionAgencyEnum getEnum(String enumDesc){
		if(StringUtils.isBlank(enumDesc))
			return null;
		SupervisionAgencyEnum[] types = SupervisionAgencyEnum.values();
		for(int i =0 ;i<types.length;i++){
			if(types[i].desc().equals(enumDesc))
				return types[i];
		}
		//没有匹配的desc
		return null;
	}
}
