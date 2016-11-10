package com.cana.vbam.common.asset.underlyingasset.dto;

import java.io.Serializable;

/**
 * 查询可以转为基础资产的保理放款列表
 * 
 * @author XuMeng
 */
public class QueryFactorLoanForUnderlyingAssetResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String loanInfoId; // 放款编号
	private String customerName; // 融资客户
	private String financeAmount; // 融资金额，单位元
	private String financeBalance; // 融资余额，单位元
	private String dueDate; // 到期日
	private String settleStatusDesc; // 中文结清状态

	public String getLoanInfoId() {
		return loanInfoId;
	}

	public void setLoanInfoId(String loanInfoId) {
		this.loanInfoId = loanInfoId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getFinanceAmount() {
		return financeAmount;
	}

	public void setFinanceAmount(String financeAmount) {
		this.financeAmount = financeAmount;
	}

	public String getFinanceBalance() {
		return financeBalance;
	}

	public void setFinanceBalance(String financeBalance) {
		this.financeBalance = financeBalance;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getSettleStatusDesc() {
		return settleStatusDesc;
	}

	public void setSettleStatusDesc(String settleStatusDesc) {
		this.settleStatusDesc = settleStatusDesc;
	}

}
