package com.cana.vbam.common.early.warning.dto;

import java.util.Date;

import com.cana.vbam.common.dto.Pagination;
import com.travelzen.framework.spring.web.format.annotation.DateFormat;

public class EarlyWarningEventHistoryRequest extends Pagination {

	private static final long serialVersionUID = -2007361346864258426L;

	private String memberId;
	
	private String productId;
	
	@DateFormat
	private Date entryReviewTimeStart;
	
	@DateFormat
	private Date entryReviewTimeEnd;
	
	@DateFormat
	private Date occurTimeStart;
	
	@DateFormat
	private Date occurTimeEnd;

	private String earlywarningType;
	
	private Boolean isCancel;
	
	private String order = "cancel_review_time, entry_review_time desc, occur_time desc";

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Date getEntryReviewTimeStart() {
		return entryReviewTimeStart;
	}

	public void setEntryReviewTimeStart(Date entryReviewTimeStart) {
		this.entryReviewTimeStart = entryReviewTimeStart;
	}

	public Date getEntryReviewTimeEnd() {
		return entryReviewTimeEnd;
	}

	public void setEntryReviewTimeEnd(Date entryReviewTimeEnd) {
		this.entryReviewTimeEnd = entryReviewTimeEnd;
	}

	public Date getOccurTimeStart() {
		return occurTimeStart;
	}

	public void setOccurTimeStart(Date occurTimeStart) {
		this.occurTimeStart = occurTimeStart;
	}

	public Date getOccurTimeEnd() {
		return occurTimeEnd;
	}

	public void setOccurTimeEnd(Date occurTimeEnd) {
		this.occurTimeEnd = occurTimeEnd;
	}

	public String getEarlywarningType() {
		return earlywarningType;
	}

	public void setEarlywarningType(String earlywarningType) {
		this.earlywarningType = earlywarningType;
	}
	
	public Boolean getIsCancel() {
		return isCancel;
	}

	public void setIsCancel(Boolean isCancel) {
		this.isCancel = isCancel;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
	
}
