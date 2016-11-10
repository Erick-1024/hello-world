package com.cana.vbam.common.asset.enums;

/**
 * @author yihong.tang
 * @time 2016.9.1
 */
public enum UnderlyingAssetOperateTypeEnum {
	
	CREATE("新建"),
	UPDATE("修改"),
	DELETE("删除"),
	ENTER_ALTERNATE_POOL("入备选池"), 
	OUT_ALTERNATE_POOL("出备选池"), 
	ENTER_POOL("入池"), 
	PENDING_ENTER_POOL("待入池"),
	OUT_POOL("出池"),
	REDEMPTION("赎回");

	private String desc;

	UnderlyingAssetOperateTypeEnum(String desc) {
		this.desc = desc;
	}

	public String desc() {
		return desc;
	}

}
