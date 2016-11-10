package com.cana.vbam.common.credit.dto.apply;

import java.io.Serializable;
import java.math.BigDecimal;

public class AutomaticAuditDataDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer cooperationPeriod;//合作月份
	
	private BigDecimal overdueRate;//逾期率
	
	private Integer overdueTimes;//逾期次数
	
	private Integer overdueDays;//逾期天数
	
	public Integer getCooperationPeriod() {
		return cooperationPeriod;
	}
	public void setCooperationPeriod(Integer cooperationPeriod) {
		this.cooperationPeriod = cooperationPeriod;
	}
	public BigDecimal getOverdueRate() {
		return overdueRate;
	}
	public void setOverdueRate(BigDecimal overdueRate) {
		this.overdueRate = overdueRate;
	}
	public Integer getOverdueTimes() {
		return overdueTimes;
	}
	public void setOverdueTimes(Integer overdueTimes) {
		this.overdueTimes = overdueTimes;
	}
	public Integer getOverdueDays() {
		return overdueDays;
	}
	public void setOverdueDays(Integer overdueDays) {
		this.overdueDays = overdueDays;
	}
    
}
