package com.cana.vbam.common.asset.underlyingasset.enums;

public enum RequestDirection {
	ASSET_POOL("资产池"),
	UNDERLYING_ASSET("基础资产");

	private String desc;

	private RequestDirection(String desc) {
		this.desc = desc;
	}

	public String desc() {
		return desc;
	}

}
