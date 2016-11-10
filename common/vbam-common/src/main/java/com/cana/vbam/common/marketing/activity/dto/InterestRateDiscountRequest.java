package com.cana.vbam.common.marketing.activity.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.cana.vbam.common.repayment.enums.InterestRateUnit;

/**
 * 获取利率折扣请求
 * @author renshui
 *
 */
public class InterestRateDiscountRequest implements Serializable{

	private static final long serialVersionUID = 4964026241041860016L;
	
	// 产品
	private String productId;
	// 融资客户id
	private String financeId;
	// 保理商id
	private String factorId;
	// 原始利率单位
	private InterestRateUnit originInterestRateUnit;
	// 原始利率
	private BigDecimal originInterestRate;
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getFinanceId() {
		return financeId;
	}
	public void setFinanceId(String financeId) {
		this.financeId = financeId;
	}
	public String getFactorId() {
		return factorId;
	}
	public void setFactorId(String factorId) {
		this.factorId = factorId;
	}
	public InterestRateUnit getOriginInterestRateUnit() {
		return originInterestRateUnit;
	}
	public void setOriginInterestRateUnit(InterestRateUnit originInterestRateUnit) {
		this.originInterestRateUnit = originInterestRateUnit;
	}
	public BigDecimal getOriginInterestRate() {
		return originInterestRate;
	}
	public void setOriginInterestRate(BigDecimal originInterestRate) {
		this.originInterestRate = originInterestRate;
	}
	
}
