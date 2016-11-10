package com.cana.vbam.common.credit.dto.white;

import com.cana.vbam.common.credit.openapi.TravelzenBaseResponse;

public class WhiteListResponseDTO extends TravelzenBaseResponse{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 是否在白名单中
	 */
	private Boolean inWhiteList;

	public Boolean getInWhiteList() {
		return inWhiteList;
	}

	public void setInWhiteList(Boolean inWhiteList) {
		this.inWhiteList = inWhiteList;
	}
	
}
