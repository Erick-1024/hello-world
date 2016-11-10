package com.cana.vbam.common.credit.openapi;

import java.io.Serializable;

import com.cana.vbam.common.repayment.dto.RepaymentAmount;

public class CreditCustomerFinanceInfoResponse extends TravelzenBaseResponse implements Serializable{

	private static final long serialVersionUID = 1l;

	private String status;//额度状态
	
	private Long totalLimit;//总额度
	
	private Long unusedLimit;//未使用额度
	
	private RepaymentAmount repaymentAmount;//融资金额信息
	
	private String canaUrlName;

	private String canaUrl;

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

	public RepaymentAmount getRepaymentAmount() {
		return repaymentAmount;
	}

	public void setRepaymentAmount(RepaymentAmount repaymentAmount) {
		this.repaymentAmount = repaymentAmount;
	}

	public String getCanaUrlName() {
		return canaUrlName;
	}

	public void setCanaUrlName(String canaUrlName) {
		this.canaUrlName = canaUrlName;
	}

	public String getCanaUrl() {
		return canaUrl;
	}

	public void setCanaUrl(String canaUrl) {
		this.canaUrl = canaUrl;
	}

}
