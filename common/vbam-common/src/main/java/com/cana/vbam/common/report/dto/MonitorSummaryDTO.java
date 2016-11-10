package com.cana.vbam.common.report.dto;

import java.io.Serializable;

public class MonitorSummaryDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 客户名称（企业全称）
	private String customerName;
	
	// 外部平台客户名称
	private String outCustomerName;
	
	// 已使用的额度（精度到分）
	private String usedLimit;
	
	// 质押率。34.22%
	private String pledgeRage;
	
	// 质押反担保率。54.11%
	private String counterGuaranteeRate;
	
	// 销售变化率。56.22%
	private String salesChangeRate;
	
	// 销售回款率。58.22%
	private String salesRepaymentRate;
	
	// 不用于显示
	private String memberId;
	
	// 不用于显示
	private String outCustomerId;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getOutCustomerName() {
		return outCustomerName;
	}

	public void setOutCustomerName(String outCustomerName) {
		this.outCustomerName = outCustomerName;
	}

	public String getUsedLimit() {
		return usedLimit;
	}

	public void setUsedLimit(String usedLimit) {
		this.usedLimit = usedLimit;
	}

	public String getPledgeRage() {
		return pledgeRage;
	}

	public void setPledgeRage(String pledgeRage) {
		this.pledgeRage = pledgeRage;
	}

	public String getCounterGuaranteeRate() {
		return counterGuaranteeRate;
	}

	public void setCounterGuaranteeRate(String counterGuaranteeRate) {
		this.counterGuaranteeRate = counterGuaranteeRate;
	}

	public String getSalesChangeRate() {
		return salesChangeRate;
	}

	public void setSalesChangeRate(String salesChangeRate) {
		this.salesChangeRate = salesChangeRate;
	}

	public String getSalesRepaymentRate() {
		return salesRepaymentRate;
	}

	public void setSalesRepaymentRate(String salesRepaymentRate) {
		this.salesRepaymentRate = salesRepaymentRate;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getOutCustomerId() {
		return outCustomerId;
	}

	public void setOutCustomerId(String outCustomerId) {
		this.outCustomerId = outCustomerId;
	}

}
