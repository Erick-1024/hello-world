package com.cana.vbam.common.marketing.activity.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.cana.vbam.common.repayment.enums.InterestRateUnit;

/**
 * 获取利率折扣响应
 * @author renshui
 *
 */
public class InterestRateDiscountResponse implements Serializable{

	private static final long serialVersionUID = -8629775132903333519L;
	// 利率单位
	private InterestRateUnit interestRateUnit;
	// 利率
	private BigDecimal interestRate;
	// 相关的折扣信息
	private InterestRateDiscount discountInfo;
	
	public InterestRateUnit getInterestRateUnit() {
		return interestRateUnit;
	}
	public void setInterestRateUnit(InterestRateUnit interestRateUnit) {
		this.interestRateUnit = interestRateUnit;
	}
	public BigDecimal getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}
	public InterestRateDiscount getDiscountInfo() {
		return discountInfo;
	}
	public void setDiscountInfo(InterestRateDiscount discountInfo) {
		this.discountInfo = discountInfo;
	}
	
}
