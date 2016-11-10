package com.travelzen.framework.core.dict;

public enum TmpRejectReason {
	PNR_without_authorization("PNR没有授权"),
	The_lack_of_effective_certificate("缺少有效证件"),
	The_lack_of_CTCT("缺少CTCT项"),
	Other("其他");

	private String desc;

	private TmpRejectReason(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}
}
