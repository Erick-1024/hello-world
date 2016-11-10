package com.cana.vbam.common.asset.enums;

/**
 * 基础资产的资产池状态
 * @author XuMeng
 *
 */
public enum UnderlyingAssetPoolStatus {

	NOT_ENTER("未入池"),
	ENTERING("待入池"),
	ENTERED("入池");
	
	private String desc;
	
	private UnderlyingAssetPoolStatus(String desc){
		this.desc = desc;
	}

	public String desc() {
		return desc;
	}
}
