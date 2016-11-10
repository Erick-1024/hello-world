package com.cana.vbam.common.report.dto;

import java.io.Serializable;

public class MonitorMetricDataDTO extends MonitorBaseData implements Serializable {

	private static final long serialVersionUID = 1L;

	// 质押反担保覆盖率（32.55%）
	private String counterGuaranteeRate;
	
	// 销售变化率（23.88%）
	private String salesChangeRate;
	
	// 销售回款率（54.90%）
	private String salesRepaymentRate;

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

}
