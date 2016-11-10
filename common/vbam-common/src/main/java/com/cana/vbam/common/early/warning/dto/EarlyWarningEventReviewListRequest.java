package com.cana.vbam.common.early.warning.dto;

import java.util.Date;

import com.cana.vbam.common.dto.Pagination;
import com.travelzen.framework.spring.web.format.annotation.DateFormat;

public class EarlyWarningEventReviewListRequest extends Pagination {

	private static final long serialVersionUID = -6046273341376299849L;

	private String productId;
	
	private String companyName;
	
	@DateFormat
	private Date auditTimeStart;

	@DateFormat
	private Date auditTimeEnd;
	
	// 当值为normal时，查询没有预警的客户
	private String earlywarningLevel;
	
	private String earlywarningEventAction;
	
	private String earlywarningReviewState;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Date getAuditTimeStart() {
		return auditTimeStart;
	}

	public void setAuditTimeStart(Date auditTimeStart) {
		this.auditTimeStart = auditTimeStart;
	}

	public Date getAuditTimeEnd() {
		return auditTimeEnd;
	}

	public void setAuditTimeEnd(Date auditTimeEnd) {
		this.auditTimeEnd = auditTimeEnd;
	}

	public String getEarlywarningLevel() {
		return earlywarningLevel;
	}

	public void setEarlywarningLevel(String earlywarningLevel) {
		this.earlywarningLevel = earlywarningLevel;
	}

	public String getEarlywarningEventAction() {
		return earlywarningEventAction;
	}

	public void setEarlywarningEventAction(String earlywarningEventAction) {
		this.earlywarningEventAction = earlywarningEventAction;
	}

	public String getEarlywarningReviewState() {
		return earlywarningReviewState;
	}

	public void setEarlywarningReviewState(String earlywarningReviewState) {
		this.earlywarningReviewState = earlywarningReviewState;
	}
	
}
