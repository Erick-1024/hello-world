package com.cana.vbam.common.marketing.activity.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.cana.vbam.common.marketing.activity.enums.InterestRateDiscountType;

public class InterestRateDiscount implements Serializable{
	
	private static final long serialVersionUID = -7638107564655372530L;

	// 折扣类型
	private InterestRateDiscountType type;  
	// 折扣率
	private BigDecimal ratio;
	// 折扣金额
	private BigDecimal amount;
	// 开始日期
	private Date startDate;
	// 结束日期
	private Date endDate;
	// 使用的促销活动id
	private String activityId;
	
	public InterestRateDiscountType getType() {
		return type;
	}
	public void setType(InterestRateDiscountType type) {
		this.type = type;
	}
	public BigDecimal getRatio() {
		return ratio;
	}
	public void setRatio(BigDecimal ratio) {
		this.ratio = ratio;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

}
