/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.vbam.common.credit.dto.limit;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 授信额度计算规则
 * 
 * @author yihong.tang
 *
 */
public class CreditLimitRuleDTO2 implements Serializable {

	private static final long serialVersionUID = -5877459910342578494L;

	private BigDecimal growthRateMin;// 销售增长率最小值
	private BigDecimal growthRateMax;// 销售增长率最大值
	private String pledgeRate;// 质押率

	public CreditLimitRuleDTO2(BigDecimal growthRateMin,BigDecimal growthRateMax,String pledgeRate){
		this.growthRateMin = growthRateMin;
		this.growthRateMax = growthRateMax;
		this.pledgeRate = pledgeRate;
	}

	public String getPledgeRate() {
		return pledgeRate;
	}

	public void setPledgeRate(String pledgeRate) {
		this.pledgeRate = pledgeRate;
	}

	public BigDecimal getGrowthRateMin() {
		return growthRateMin;
	}


	public void setGrowthRateMin(BigDecimal growthRateMin) {
		this.growthRateMin = growthRateMin;
	}

	public BigDecimal getGrowthRateMax() {
		return growthRateMax;
	}

	public void setGrowthRateMax(BigDecimal growthRateMax) {
		this.growthRateMax = growthRateMax;
	}

}
