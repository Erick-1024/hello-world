package com.cana.vbam.common.early.warning.dto;

public class CanelEarlyWarningEventRequest extends EarlyWarningEventCommonRequest {

	private static final long serialVersionUID = 1L;

	// 预警事件ID
	private String earlywarningEventId;
	
	// 解除说明
	private String cancelExtra;

	public String getEarlywarningEventId() {
		return earlywarningEventId;
	}

	public void setEarlywarningEventId(String earlywarningEventId) {
		this.earlywarningEventId = earlywarningEventId;
	}

	public String getCancelExtra() {
		return cancelExtra;
	}

	public void setCancelExtra(String cancelExtra) {
		this.cancelExtra = cancelExtra;
	}
	
}
