package com.cana.vbam.common.credit.dto.limit;

import java.math.BigDecimal;

public class CalculateLimitResult {

	private Long finalLimit;
	@Deprecated private BigDecimal growthFinalRate;
	@Deprecated private BigDecimal pledgeRate;
	
	public CalculateLimitResult(Long finalLimit){
		this.finalLimit = finalLimit;
	}
	
	public CalculateLimitResult(){
	}
	
	public Long getFinalLimit() {
		return finalLimit;
	}
	public void setFinalLimit(Long finalLimit) {
		this.finalLimit = finalLimit;
	}
	@Deprecated public BigDecimal getGrowthFinalRate() {
		return growthFinalRate;
	}
	@Deprecated public void setGrowthFinalRate(BigDecimal growthFinalRate) {
		this.growthFinalRate = growthFinalRate;
	}
	@Deprecated public BigDecimal getPledgeRate() {
		return pledgeRate;
	}
	@Deprecated public void setPledgeRate(BigDecimal pledgeRate) {
		this.pledgeRate = pledgeRate;
	}
	
}
