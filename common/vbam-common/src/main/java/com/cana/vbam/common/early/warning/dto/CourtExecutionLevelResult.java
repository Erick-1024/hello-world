package com.cana.vbam.common.early.warning.dto;

import com.cana.vbam.common.early.warning.enums.EarlywarningLevel;

public class CourtExecutionLevelResult {

	private EarlywarningLevel earlywarningLevel;
	
	private long totalExecutionAmount;
	
	private long count;

	public EarlywarningLevel getEarlywarningLevel() {
		return earlywarningLevel;
	}

	public void setEarlywarningLevel(EarlywarningLevel earlywarningLevel) {
		this.earlywarningLevel = earlywarningLevel;
	}

	public long getTotalExecutionAmount() {
		return totalExecutionAmount;
	}

	public void setTotalExecutionAmount(long totalExecutionAmount) {
		this.totalExecutionAmount = totalExecutionAmount;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}	
	
}
