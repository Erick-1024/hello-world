package com.cana.yundaex.common.dto.limit;

import com.cana.yundaex.common.dto.YundaexBaseResponse;

public class YdQueryCreditLimitResponse extends YundaexBaseResponse{

	private static final long serialVersionUID = 1L;
	
	private String status;      //额度状态
	
	private Long totalLimit;    //总额度
	
	private Long unusedLimit;	 //未使用额度

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getTotalLimit() {
		return totalLimit;
	}

	public void setTotalLimit(Long totalLimit) {
		this.totalLimit = totalLimit;
	}

	public Long getUnusedLimit() {
		return unusedLimit;
	}

	public void setUnusedLimit(Long unusedLimit) {
		this.unusedLimit = unusedLimit;
	}
	
	
}
