package com.cana.vbam.common.asset.enums;

/**
 * 基础资产来源
 * @author XuMeng
 *
 */
public enum UnderlyingAssetSource {

	MANUAL("手工导入的基础资产"),
	FACTOR("保理业务放款");
	
	private String desc;
	
	private UnderlyingAssetSource(String desc){
		this.desc = desc;
	}

	public String desc() {
		return desc;
	}
}
