package com.cana.yundaex.common.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class InterestRateDTO implements Serializable {
	private static final long serialVersionUID = -1501229693801927779L;

	private String id;

	/**
	 * 还款方式
	 */
	private String repaymentMethod;

	/**
	 * 利率
	 */
	private BigDecimal interestRate;

	private Date createTime;

	private Date updateTime;

	/**
	 * 客户id
	 */
	private String customerId;

	/**
	 * 利率单位
	 */
	private String rateUnit;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRepaymentMethod() {
		return repaymentMethod;
	}

	public void setRepaymentMethod(String repaymentMethod) {
		this.repaymentMethod = repaymentMethod;
	}

	public BigDecimal getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getRateUnit() {
		return rateUnit;
	}

	public void setRateUnit(String rateUnit) {
		this.rateUnit = rateUnit;
	}

}
