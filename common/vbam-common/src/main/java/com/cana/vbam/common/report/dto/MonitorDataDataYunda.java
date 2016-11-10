package com.cana.vbam.common.report.dto;

import java.io.Serializable;


public class MonitorDataDataYunda extends MonitorBaseDataYunda implements Serializable{

	private static final long serialVersionUID = -7873290533119726272L;
	
	
	//保证金余额
	private Long bailBalance;
	
	//净现金流增长量
	private Long netCashflowGrowth;
	
	//最大授信金额
	private Long creditLimit;
	
	//逾期次数
	private Long overdues;

	public Long getBailBalance() {
		return bailBalance;
	}

	public void setBailBalance(Long bailBalance) {
		this.bailBalance = bailBalance;
	}

	public Long getNetCashflowGrowth() {
		return netCashflowGrowth;
	}

	public void setNetCashflowGrowth(Long netCashflowGrowth) {
		this.netCashflowGrowth = netCashflowGrowth;
	}

	public Long getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(Long creditLimit) {
		this.creditLimit = creditLimit;
	}

	public Long getOverdues() {
		return overdues;
	}

	public void setOverdues(Long overdues) {
		this.overdues = overdues;
	}
	
	
	
}
