package com.cana.vbam.common.asset.enums;

public enum BusinessMode {
	SINGLE("逐笔");

	private String desc;

	private BusinessMode(String desc) {
		this.desc = desc;
	}

	public String desc() {
		return desc;
	}
}
