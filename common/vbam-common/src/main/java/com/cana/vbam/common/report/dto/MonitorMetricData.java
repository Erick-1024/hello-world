package com.cana.vbam.common.report.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class MonitorMetricData extends MonitorBaseData implements Serializable{

	private static final long serialVersionUID = 1L;

	private BigDecimal counterGuaranteeRate;
	
	private BigDecimal salesChangeRate;
	
	private BigDecimal salesRepaymentRate;

	public BigDecimal getCounterGuaranteeRate() {
		return counterGuaranteeRate;
	}

	public void setCounterGuaranteeRate(BigDecimal counterGuaranteeRate) {
		this.counterGuaranteeRate = counterGuaranteeRate;
	}

	public BigDecimal getSalesChangeRate() {
		return salesChangeRate;
	}

	public void setSalesChangeRate(BigDecimal salesChangeRate) {
		this.salesChangeRate = salesChangeRate;
	}

	public BigDecimal getSalesRepaymentRate() {
		return salesRepaymentRate;
	}

	public void setSalesRepaymentRate(BigDecimal salesRepaymentRate) {
		this.salesRepaymentRate = salesRepaymentRate;
	}
	
}
