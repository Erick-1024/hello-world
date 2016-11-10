package com.cana.vbam.common.credit.dto.limit;

import com.cana.vbam.common.credit.openapi.TravelzenBaseResponse;

public class PreGenerateCreditLimitResponse extends TravelzenBaseResponse {

	private static final long serialVersionUID = 1L;

	private Long totalLimit;

	private boolean existLimit;

	public Long getTotalLimit() {
		return totalLimit;
	}

	public void setTotalLimit(Long totalLimit) {
		this.totalLimit = totalLimit;
	}

	public boolean isExistLimit() {
		return existLimit;
	}

	public void setExistLimit(boolean existLimit) {
		this.existLimit = existLimit;
	}

}
