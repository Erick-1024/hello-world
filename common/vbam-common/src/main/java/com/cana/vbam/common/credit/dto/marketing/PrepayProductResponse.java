package com.cana.vbam.common.credit.dto.marketing;

import com.cana.vbam.common.credit.openapi.TravelzenBaseResponse;

public class PrepayProductResponse extends TravelzenBaseResponse {

	private static final long serialVersionUID = 1L;

	// 放款期限单位
	private String loanPeriodUnit;
	
	// 放款期限
	private int loanPeriod;
	
	// 手续费单位
	private String interestRateUnit;
	
	// 手续费费率
	private String interestRate;
	
	// 预支付金额
	private long prepayAmount;
	
	// 手续费金额
	private long interestAmount;
	
	// 促销活动ID
	private String activityId;
	
	// 促销开始时间
	private String startTime;
	
	// 促销结束时间
	private String endTime;
	
	// 原手续费单位
	private String originInterestRateUnit;
	
	// 原手续费费率
	private String originInterestRate;
	
	// 折扣
	private String discount;

	public String getLoanPeriodUnit() {
		return loanPeriodUnit;
	}

	public void setLoanPeriodUnit(String loanPeriodUnit) {
		this.loanPeriodUnit = loanPeriodUnit;
	}

	public int getLoanPeriod() {
		return loanPeriod;
	}

	public void setLoanPeriod(int loanPeriod) {
		this.loanPeriod = loanPeriod;
	}

	public String getInterestRateUnit() {
		return interestRateUnit;
	}

	public void setInterestRateUnit(String interestRateUnit) {
		this.interestRateUnit = interestRateUnit;
	}

	public String getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}

	public long getPrepayAmount() {
		return prepayAmount;
	}

	public void setPrepayAmount(long prepayAmount) {
		this.prepayAmount = prepayAmount;
	}

	public long getInterestAmount() {
		return interestAmount;
	}

	public void setInterestAmount(long interestAmount) {
		this.interestAmount = interestAmount;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getOriginInterestRateUnit() {
		return originInterestRateUnit;
	}

	public void setOriginInterestRateUnit(String originInterestRateUnit) {
		this.originInterestRateUnit = originInterestRateUnit;
	}

	public String getOriginInterestRate() {
		return originInterestRate;
	}

	public void setOriginInterestRate(String originInterestRate) {
		this.originInterestRate = originInterestRate;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}
	
}
