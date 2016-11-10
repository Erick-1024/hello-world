package com.cana.vbam.common.early.warning.dto;

import java.util.Date;

import com.cana.vbam.common.dto.Pagination;
import com.travelzen.framework.spring.web.format.annotation.DateFormat;

public class EarlyWarningLevelChangeHistoryRequest extends Pagination {

	private static final long serialVersionUID = -1147635778595860376L;

	private String productId;
	
	@DateFormat
	private Date inTimeStart;
	
	@DateFormat
	private Date inTimeEnd;
	
	@DateFormat
	private Date outTimeStart;
	
	@DateFormat
	private Date outTimeEnd;
	
	private String companyName;
	
	private String earlywarningLevel;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Date getInTimeStart() {
		return inTimeStart;
	}

	public void setInTimeStart(Date inTimeStart) {
		this.inTimeStart = inTimeStart;
	}

	public Date getInTimeEnd() {
		return inTimeEnd;
	}

	public void setInTimeEnd(Date inTimeEnd) {
		this.inTimeEnd = inTimeEnd;
	}

	public Date getOutTimeStart() {
		return outTimeStart;
	}

	public void setOutTimeStart(Date outTimeStart) {
		this.outTimeStart = outTimeStart;
	}

	public Date getOutTimeEnd() {
		return outTimeEnd;
	}

	public void setOutTimeEnd(Date outTimeEnd) {
		this.outTimeEnd = outTimeEnd;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getEarlywarningLevel() {
		return earlywarningLevel;
	}

	public void setEarlywarningLevel(String earlywarningLevel) {
		this.earlywarningLevel = earlywarningLevel;
	}
	
}
