package com.cana.vbam.common.asset.enums;

public enum ActionMode {
	REDIS_UPDATE("redis修改"),
	REDIS_QUERY("redis查询"),
	REDIS_DEL("redis删除"),
	ADD("新增"),
	UPDATE("修改"),
	QUERY("查询"),
	DEL("删除");

	private String desc;

	private ActionMode(String desc) {
		this.desc = desc;
	}

	public String desc() {
		return desc;
	}

}
