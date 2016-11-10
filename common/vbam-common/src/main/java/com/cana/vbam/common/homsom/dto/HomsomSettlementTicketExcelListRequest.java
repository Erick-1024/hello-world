package com.cana.vbam.common.homsom.dto;

import com.cana.vbam.common.dto.Pagination;

public class HomsomSettlementTicketExcelListRequest extends Pagination {

	private static final long serialVersionUID = 7058309891612691463L;

	private String rediskey;
	
	private boolean passed;
	
	private String userId;

	public String getRediskey() {
		return rediskey;
	}

	public void setRediskey(String rediskey) {
		this.rediskey = rediskey;
	}

	public boolean isPassed() {
		return passed;
	}

	public void setPassed(boolean passed) {
		this.passed = passed;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
