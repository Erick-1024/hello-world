package com.cana.vbam.common.early.warning.dto;

public class AuditEarlyWarningEventRequest extends EarlyWarningEventCommonRequest {

	private static final long serialVersionUID = 1L;

	// 预警审核ID
	private String earlywarningEventReviewId;
	
	// 审核结果
	private boolean result;

	public String getEarlywarningEventReviewId() {
		return earlywarningEventReviewId;
	}

	public void setEarlywarningEventReviewId(String earlywarningEventReviewId) {
		this.earlywarningEventReviewId = earlywarningEventReviewId;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}
	
}
