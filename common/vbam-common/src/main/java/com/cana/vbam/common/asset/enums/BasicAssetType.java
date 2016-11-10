package com.cana.vbam.common.asset.enums;

/**
 * 基础资产类型
 * @author jiangzhou.Ren
 * @time 2016年9月2日上午10:54:59
 */
public enum BasicAssetType {
	
	FACTOR("保理");
	
	private String desc;
	
	private BasicAssetType(String desc){
		this.desc = desc;
	}

	public String desc() {
		return desc;
	}

}
