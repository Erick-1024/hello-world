/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.vbam.common.credit.dto.limit;

import java.io.Serializable;

/**
 * 授信额度计算规则
 * 
 * @author ducer
 *
 */
public class CreditLimitRuleDTO implements Serializable, Comparable<CreditLimitRuleDTO> {

	private static final long serialVersionUID = -5877459910342578494L;

	private String saleGrowthRateOfMonth;// 同期月销售增长率
	private String saleGrowthRateFinalValue;// 销售增长率定值
	private String pledgeRate;// 质押率

	public CreditLimitRuleDTO(String saleGrowthRateOfMonth,String saleGrowthRateFinalValue,String pledgeRate){
		this.saleGrowthRateOfMonth = saleGrowthRateOfMonth;
		this.saleGrowthRateFinalValue = saleGrowthRateFinalValue;
		this.pledgeRate = pledgeRate;
	}
	
	public String getSaleGrowthRateOfMonth() {
		return saleGrowthRateOfMonth;
	}

	public void setSaleGrowthRateOfMonth(String saleGrowthRateOfMonth) {
		this.saleGrowthRateOfMonth = saleGrowthRateOfMonth;
	}

	public String getSaleGrowthRateFinalValue() {
		return saleGrowthRateFinalValue;
	}

	public void setSaleGrowthRateFinalValue(String saleGrowthRateFinalValue) {
		this.saleGrowthRateFinalValue = saleGrowthRateFinalValue;
	}

	public String getPledgeRate() {
		return pledgeRate;
	}

	public void setPledgeRate(String pledgeRate) {
		this.pledgeRate = pledgeRate;
	}

	@Override
	public int compareTo(CreditLimitRuleDTO o) {
		return this.getSaleGrowthRateOfMonth().compareTo(o.getSaleGrowthRateOfMonth());
	}

}
