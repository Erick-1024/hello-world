package com.cana.vbam.common.repayment.dto;

import java.io.Serializable;

public class PaidRepaymentExpenseRedisDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7978968577756323712L;

	/**
	 * 主键
	 */
	private String id;

	/**
	 * 放款编号
	 */
	private String loanNo;
	
	/**
	 * 放款信息Id
	 */
	private String loanInfoId;

	/**
	 * 费用明目
	 */
	private String expenseSubject;

	/**
	 * 还款日
	 */
	private String repaymentDate;

	/**
	 * 已还金额
	 */
	private String paidAmount;

	/**
	 * 计费方式
	 */
	private String chargeMethod;

	/**
	 * 计费基准（融资余额、保理余额、其他）
	 */
	private String chargeStandard;

	/**
	 * 计费比率（计费方式选择比率情况使用）
	 */
	private String chargeRatio;

	/**
	 * 计费定额（当选择计费方式是定额情况使用）
	 */
	private String chargeAmount;
	
	/**
	 * 计费值
	 */
	private String chargeValue;

	/**
	 * 应还金额
	 */
	private String repaymentAmount;
	
	/**
	 * 结清状态
	 */
	private String SettleStatus;
	
	/**
	 * 用于页面显示用的结清状态
	 */
	private String settleStatusForPage;
	
	/**
	 * 已还金额New
	 */
	private String paidAmountNew;
	
	/**
	 * 应还金额New
	 */
	private String repaymentAmountNew;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public String getLoanInfoId() {
		return loanInfoId;
	}

	public void setLoanInfoId(String loanInfoId) {
		this.loanInfoId = loanInfoId;
	}

	public String getExpenseSubject() {
		return expenseSubject;
	}

	public void setExpenseSubject(String expenseSubject) {
		this.expenseSubject = expenseSubject;
	}

	public String getRepaymentDate() {
		return repaymentDate;
	}

	public void setRepaymentDate(String repaymentDate) {
		this.repaymentDate = repaymentDate;
	}

	public String getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(String paidAmount) {
		this.paidAmount = paidAmount;
	}

	public String getChargeMethod() {
		return chargeMethod;
	}

	public void setChargeMethod(String chargeMethod) {
		this.chargeMethod = chargeMethod;
	}

	public String getChargeStandard() {
		return chargeStandard;
	}

	public void setChargeStandard(String chargeStandard) {
		this.chargeStandard = chargeStandard;
	}

	public String getChargeRatio() {
		return chargeRatio;
	}

	public void setChargeRatio(String chargeRatio) {
		this.chargeRatio = chargeRatio;
	}

	public String getChargeAmount() {
		return chargeAmount;
	}

	public void setChargeAmount(String chargeAmount) {
		this.chargeAmount = chargeAmount;
	}

	public String getRepaymentAmount() {
		return repaymentAmount;
	}

	public void setRepaymentAmount(String repaymentAmount) {
		this.repaymentAmount = repaymentAmount;
	}

	public String getSettleStatus() {
		return SettleStatus;
	}

	public void setSettleStatus(String settleStatus) {
		SettleStatus = settleStatus;
	}

	public String getChargeValue() {
		return chargeValue;
	}

	public void setChargeValue(String chargeValue) {
		this.chargeValue = chargeValue;
	}

	public String getSettleStatusForPage() {
		return settleStatusForPage;
	}

	public void setSettleStatusForPage(String settleStatusForPage) {
		this.settleStatusForPage = settleStatusForPage;
	}

	public String getPaidAmountNew() {
		return paidAmountNew;
	}

	public void setPaidAmountNew(String paidAmountNew) {
		this.paidAmountNew = paidAmountNew;
	}

	public String getRepaymentAmountNew() {
		return repaymentAmountNew;
	}

	public void setRepaymentAmountNew(String repaymentAmountNew) {
		this.repaymentAmountNew = repaymentAmountNew;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PaidRepaymentExpenseRedisDTO other = (PaidRepaymentExpenseRedisDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
