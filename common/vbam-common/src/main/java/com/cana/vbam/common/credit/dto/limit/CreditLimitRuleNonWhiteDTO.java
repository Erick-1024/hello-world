/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.vbam.common.credit.dto.limit;

import java.io.Serializable;

/**
 * 非白名单授信额度计算规则
 * 
 * @author sugar
 *
 */
public class CreditLimitRuleNonWhiteDTO implements Serializable{

	private static final long serialVersionUID = -5877459910342578494L;

	private Integer cooperationPeriodMin;// 跟真旅网合作期限最小值
	private Integer cooperationPeriodMax;// 跟真旅网合作期限最大值
	private String depositRate;// 折扣率

	public CreditLimitRuleNonWhiteDTO(Integer cooperationPeriodMin,Integer cooperationPeriodMax,String depositRate){
		this.cooperationPeriodMin = cooperationPeriodMin;
		this.cooperationPeriodMax = cooperationPeriodMax;
		this.depositRate = depositRate;
	}

	public Integer getCooperationPeriodMin() {
		return cooperationPeriodMin;
	}

	public void setCooperationPeriodMin(Integer cooperationPeriodMin) {
		this.cooperationPeriodMin = cooperationPeriodMin;
	}

	public Integer getCooperationPeriodMax() {
		return cooperationPeriodMax;
	}

	public void setCooperationPeriodMax(Integer cooperationPeriodMax) {
		this.cooperationPeriodMax = cooperationPeriodMax;
	}
	
	public String getDepositRate() {
		return depositRate;
	}
	
	public void setDepositRate(String depositRate) {
		this.depositRate = depositRate;
	}

}
